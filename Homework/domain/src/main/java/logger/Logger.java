package logger;

import java.util.List;

public class Logger implements LoggerStorageDao {
    private final LoggerStorageDao storage = LoggerStorageFactory.getStorage();

    @Override
    public void info(String className, String message) {
        storage.info(className, message);
    }

    @Override
    public void warning(String className, String message) {
        storage.warning(className, message);
    }

    @Override
    public void error(String className, String message) {
        storage.error(className, message);
    }

    @Override
    public void error(String className, String message, Exception e) {
        storage.error(className, message, e);
    }

    @Override
    public List<String> getLogs() {
        return storage.getLogs();
    }

    @Override
    public List<String> getSortedLogsByDateAsc() {
        return storage.getSortedLogsByDateAsc();
    }

    @Override
    public List<String> getSortedLogsByDateDesc() {
        return storage.getSortedLogsByDateDesc();
    }

    @Override
    public List<String> getLogsByStatus(LogType status) {
        return storage.getLogsByStatus(status);
    }
}
