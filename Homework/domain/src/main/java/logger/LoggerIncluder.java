package logger;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

public class LoggerIncluder {
    private static final LoggerSettings settings = new LoggerSettings();

    public static Logger getLogger() {
        Field loggingType;

        try (FileInputStream in = new FileInputStream(settings.getLogPath())) {
            loggingType = settings.getClass().getDeclaredField("loggingType");
            loggingType.setAccessible(true);

            Properties properties = new Properties();
            properties.load(in);
            loggingType.set(settings, properties.getProperty("logger.storage.type"));
        } catch (IOException | NoSuchFieldException | IllegalAccessException e) {
            System.out.println(e.getMessage() + "\n" + e.getCause());
        }

        if (settings.getLoggingType().equals("file")) {
            return new LoggerWithStorageInFile();
        } else if (settings.getLoggingType().equals("console")) {
            return new LoggerWithStorageInMemory();
        } else {
            return new LoggerWithStorageInMemoryAndFile();
        }
    }

    public static void setLogger(String type) {
        try (
            FileInputStream in = new FileInputStream(settings.getLogPath());
            FileOutputStream out = new FileOutputStream(settings.getLogPath())
        ) {
            Properties properties = new Properties();
            properties.load(in);
            properties.setProperty("logger.storage.type", type.toLowerCase());
            properties.store(out, null);
        } catch (IOException e) {
            System.out.println(e.getMessage() + "\n" + e.getCause());
        }
    }
}
