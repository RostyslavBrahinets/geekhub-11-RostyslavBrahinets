import exceptions.NotFoundException;
import logger.Logger;
import logger.LoggerIncluder;
import menu.LoggerMenu;
import menu.MainMenu;

public class ApplicationStarter {
    static Logger logger;

    public static void main(String[] args) {
        LoggerMenu loggerMenu = new LoggerMenu();
        loggerMenu.runMenu();

        logger = LoggerIncluder.getLogger();
        logger.showLogs();

        while (true) {
            try {
                MainMenu mainMenu = new MainMenu();
                mainMenu.runMenu();
            } catch (NotFoundException e) {
                logger.error(ApplicationStarter.class.getName(), e.getMessage(), e);
            }
        }
    }
}
