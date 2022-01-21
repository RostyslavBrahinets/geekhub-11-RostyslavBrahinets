package logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss:ms");

    private final LogType type;
    private final String className;
    private final String message;
    private final LocalDateTime localDateTime;
    private Exception exception;

    public Log(LogType type, String className, String message, LocalDateTime localDateTime, Exception exception) {
        this.className = className;
        this.type = type;
        this.message = message;
        this.localDateTime = localDateTime;
        this.exception = exception;
    }

    public Log(LogType type, String className, String message, LocalDateTime localDateTime) {
        this.className = className;
        this.type = type;
        this.message = message;
        this.localDateTime = localDateTime;
    }

    public String getClassName() {
        return className;
    }

    public LogType getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public Exception getException() {
        return exception;
    }

    @Override
    public String toString() {
        StringBuilder logString = new StringBuilder();
        logString.append("\n[").append(type).append("] ")
            .append(className).append(": ")
            .append(message).append("\n")
            .append(localDateTime.format(formatter)).append("\n");

        if (exception != null) {
            logString.append(exception.getStackTrace()[0]).append("\n");
        }

        return logString.toString();
    }
}
