import exceptions.NotFoundException;
import logger.Logger;
import menu.LoggerMenu;
import menu.MainMenu;
import db.DataBaseConnector;
import db.DataBaseStarter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ApplicationStarter {
    static Logger logger;

    public static void main(String[] args) {
        setLogger();
        setDatabase();

        while (true) {
            try {
                MainMenu mainMenu = new MainMenu();
                mainMenu.runMenu();
            } catch (NotFoundException | SQLException | IOException e) {
                logger.error(ApplicationStarter.class.getName(), e.getMessage(), e);
            }
        }
    }

    private static void setLogger() {
        boolean loggerNotSet = true;
        while (loggerNotSet) {
            try {
                LoggerMenu loggerMenu = new LoggerMenu();
                loggerMenu.runMenu();
                logger = new Logger();
                logger.getLogs().forEach(System.out::println);
                loggerNotSet = false;
            } catch (IllegalArgumentException | NotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void setDatabase() {
        try (
                Connection connection = DataBaseConnector.getConnection();
                Statement statement = connection.createStatement()
        ) {
            DataBaseStarter repository = new DataBaseStarter();
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
        } catch (SQLException | IOException e) {
            logger.error(ApplicationStarter.class.getName(), e.getMessage(), e);
        }
    }
}
