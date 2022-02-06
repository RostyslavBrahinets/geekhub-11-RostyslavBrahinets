package logger;

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
    public void showLogs() {
        storage.showLogs();
    }

    @Override
    public void showSortedLogsByDateASC() {
        storage.showSortedLogsByDateASC();
    }

    @Override
    public void showSortedLogsByDateDESC() {
        storage.showSortedLogsByDateDESC();
    }

    @Override
    public void showLogsByStatus(LogType status) {
        storage.showLogsByStatus(status);
    }
}
