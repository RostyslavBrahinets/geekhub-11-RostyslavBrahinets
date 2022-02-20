import exceptions.NotFoundException;
import logger.Logger;
import menu.LoggerMenu;
import menu.MainMenu;
import repository.Connector;
import repository.MainRepository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ApplicationStarter {
    static Logger logger;

    public static void main(String[] args) {
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

        try (
            Connection connection = Connector.getConnection();
            Statement statement = connection.createStatement()
        ) {
            MainRepository repository = new MainRepository();
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

        while (true) {
            try {
                MainMenu mainMenu = new MainMenu();
                mainMenu.runMenu();
            } catch (NotFoundException | SQLException | IOException e) {
                logger.error(ApplicationStarter.class.getName(), e.getMessage(), e);
            }
        }
    }
}
