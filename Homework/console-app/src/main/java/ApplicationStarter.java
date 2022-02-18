import exceptions.NotFoundException;
import logger.Logger;
import menu.LoggerMenu;
import menu.MainMenu;

import java.io.IOException;
import java.sql.SQLException;

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
