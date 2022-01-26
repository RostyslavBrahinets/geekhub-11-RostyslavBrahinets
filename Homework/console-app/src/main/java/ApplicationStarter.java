import exceptions.NotFoundException;
import logger.Logger;
import logger.LoggerWithStorageInFile;
import logger.LoggerWithStorageInMemory;
import menu.MainMenu;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ApplicationStarter {
    static Logger logger;

    public static void main(String[] args)
        includeLogger();

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

    private static void includeLogger() {
        String applicationProperties = "Homework/domain/src/main/resources/application.properties";
        Properties properties = new Properties();
        try (FileInputStream in = new FileInputStream(applicationProperties)) {
            properties.load(in);
        } catch (IOException e) {
            System.out.println(e.getMessage() + "\n" + e.getCause());
        }

        String loggerStorageType = properties.getProperty("logger.storage.type");
        if (loggerStorageType.equals("file")) {
            logger = new LoggerWithStorageInFile();
        } else {
            logger = new LoggerWithStorageInMemory();
        }
    }
}
