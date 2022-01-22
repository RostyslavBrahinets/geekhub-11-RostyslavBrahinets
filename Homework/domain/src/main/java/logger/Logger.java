package logger;

public interface Logger {
    void info(String className, String message);

    void warning(String className, String message);

    void error(String className, String message);

    void error(String className, String message, Exception e);

    void showLogs();
}
