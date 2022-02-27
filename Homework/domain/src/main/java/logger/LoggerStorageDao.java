package logger;

import java.util.List;

public interface LoggerStorageDao {
    void info(String className, String message);

    void warning(String className, String message);

    void error(String className, String message);

    void error(String className, String message, Exception e);

    List<String> getLogs();

    List<String> getSortedLogsByDateAsc();

    List<String> getSortedLogsByDateDesc();

    List<String> getLogsByStatus(LogType status);
}
