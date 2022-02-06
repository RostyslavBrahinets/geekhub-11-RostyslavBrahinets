package logger;

public interface LoggerStorageDao {
    void info(String className, String message);

    void warning(String className, String message);

    void error(String className, String message);

    void error(String className, String message, Exception e);

    void showLogs();

    void showSortedLogsByDateASC();

    void showSortedLogsByDateDESC();

    void showLogsByStatus(LogType status);
}
