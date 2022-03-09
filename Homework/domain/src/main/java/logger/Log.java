package logger;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Log implements Serializable {
    @Serial
    private static final long serialVersionUID = 7L;

    private final LogType type;
    private final String className;
    private final String message;
    private final LocalDateTime localDateTime;
    private Exception exception;

    public Log(
        LogType type,
        String className,
        String message,
        LocalDateTime localDateTime,
        Exception exception) {
        this.type = type;
        this.className = className;
        this.message = message;
        this.localDateTime = localDateTime;
        this.exception = exception;
    }

    public Log(
        LogType type,
        String className,
        String message,
        LocalDateTime localDateTime
    ) {
        this.type = type;
        this.className = className;
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.ms");
        StringBuilder logString = new StringBuilder();
        logString.append("[").append(type).append("] ")
            .append(className).append(": ")
            .append(message).append(" ");

        if (exception != null) {
            logString.append(exception.getStackTrace()[0]).append(" ");
        }

        logString.append(localDateTime.format(formatter));

        return logString.toString();
    }
}
