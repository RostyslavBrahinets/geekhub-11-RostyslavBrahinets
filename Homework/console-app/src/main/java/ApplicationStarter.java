import exceptions.NotFoundException;
import logger.Logger;
import logger.LoggerStorageDao;
import logger.LoggerStorageFactory;
import menu.LoggerMenu;
import menu.MainMenu;

public class ApplicationStarter {
    static Logger logger;

    public static void main(String[] args) {
        boolean loggerNotSet = true;
        while (loggerNotSet) {
            try {
                LoggerMenu loggerMenu = new LoggerMenu();
                loggerMenu.runMenu();
                logger = new Logger();
                logger.showLogs();
                loggerNotSet = false;
            } catch (IllegalArgumentException | NotFoundException e) {
                System.out.println(e.getMessage());
            }
        }

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
