package logger;

import java.util.List;

public class Logger implements LoggerStorageDao {
    private static final LoggerStorageDao storage = LoggerStorageFactory.getStorage();

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
    public List<Log> getLogs() {
        return storage.getLogs();
    }

    @Override
    public List<Log> getSortedLogsByDateAsc() {
        return storage.getSortedLogsByDateAsc();
    }

    @Override
    public List<Log> getSortedLogsByDateDesc() {
        return storage.getSortedLogsByDateDesc();
    }

    @Override
    public List<Log> getLogsByStatus(LogType status) {
        return storage.getLogsByStatus(status);
    }
}
