package logger;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Log implements Serializable {
    @Serial
    private static final long serialVersionUID = 6L;

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

    public LogType getType() {
        return type;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Log log = (Log) o;
        return type == log.type
            && Objects.equals(className, log.className)
            && Objects.equals(message, log.message)
            && Objects.equals(localDateTime, log.localDateTime)
            && Objects.equals(exception, log.exception);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, className, message, localDateTime, exception);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss:ms");
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
