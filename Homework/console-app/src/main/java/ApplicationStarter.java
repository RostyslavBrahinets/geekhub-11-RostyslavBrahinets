import config.AppConfig;
import config.MenuConfig;
import db.DbConnectionProvider;
import db.DbStarter;
import logger.Logger;
import menu.LoggerMenu;
import menu.MainMenu;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ApplicationStarter {
    static Logger logger;

    public static void main(String[] args) {
        setLogger();
        setDatabase();

        while (true) {
            try {
                AnnotationConfigApplicationContext applicationContext =
                    new AnnotationConfigApplicationContext(MenuConfig.class);
                MainMenu mainMenu = applicationContext.getBean(MainMenu.class);
                mainMenu.runMenu();
            } catch (Exception e) {
                logger.error(ApplicationStarter.class.getName(), e.getMessage(), e);
            }
        }
    }

    private static void setLogger() {
        boolean loggerNotSet = true;
        while (loggerNotSet) {
            try {
                AnnotationConfigApplicationContext applicationContext =
                    new AnnotationConfigApplicationContext(MenuConfig.class);
                LoggerMenu loggerMenu = applicationContext.getBean(LoggerMenu.class);
                loggerMenu.runMenu();
                logger = new Logger();
                logger.getLogs().forEach(System.out::println);
                loggerNotSet = false;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void setDatabase() {
        AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(AppConfig.class);
        DbConnectionProvider dbConnectionProvider =
            applicationContext.getBean(DbConnectionProvider.class);

        try (
            Connection connection = dbConnectionProvider.getConnection();
            Statement statement = connection.createStatement()
        ) {
            DbStarter repository = applicationContext.getBean(DbStarter.class);
            repository.createTablesInDataBase();

            String sql = "select * from course";
            ResultSet resultSet = statement.executeQuery(sql);

            if (!resultSet.next()) {
                repository.insertDataToTablesInDataBase();
                logger.info(ApplicationStarter.class.getName(),
                    "Tables created in database! Data inserted to tables!");
            } else {
                logger.info(ApplicationStarter.class.getName(),
                    "Tables already created in database!");
            }
        } catch (Exception e) {
            logger.error(ApplicationStarter.class.getName(), e.getMessage(), e);
        }
    }
}
