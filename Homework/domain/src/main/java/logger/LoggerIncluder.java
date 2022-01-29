package logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

public class LoggerIncluder {
    public static Logger getLogger() {
        LoggerSettings settings = new LoggerSettings();
        Field loggingType;

        try (FileInputStream in = new FileInputStream(settings.getLogPath())) {
            loggingType = settings.getClass().getDeclaredField("loggingType");
            loggingType.setAccessible(true);

            Properties properties = new Properties();
            properties.load(in);
            loggingType.set(settings, properties.getProperty("logger.storage.type"));

            System.out.println(settings.getLoggingType());
        } catch (IOException | NoSuchFieldException | IllegalAccessException e) {
            System.out.println(e.getMessage() + "\n" + e.getCause());
        }

        if (settings.getLoggingType().equals("file")) {
            return new LoggerWithStorageInFile();
        } else {
            return new LoggerWithStorageInMemory();
        }
    }
}
