import exceptions.NotFoundException;
import logger.Logger;
import menu.MainMenu;

public class ApplicationStarter {
    public static void main(String[] args) {
        Logger.showLogsFromFile();

        while (true) {
            try {
                MainMenu mainMenu = new MainMenu();
                mainMenu.runMenu();
            } catch (NotFoundException e) {
                Logger.error(ApplicationStarter.class.getName(), e.getMessage(), e);
            }
        }
    }
}
