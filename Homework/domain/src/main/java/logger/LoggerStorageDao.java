package logger;

import java.util.List;

public interface LoggerStorageDao {
    void info(String className, String message);

    void warning(String className, String message);

    void error(String className, String message);

    void error(String className, String message, Exception e);

    List<Log> getLogs();

    List<Log> getSortedLogsByDateAsc();

    List<Log> getSortedLogsByDateDesc();

    List<Log> getLogsByStatus(LogType status);
}
