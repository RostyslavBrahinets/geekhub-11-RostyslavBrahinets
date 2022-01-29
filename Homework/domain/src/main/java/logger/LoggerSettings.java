package logger;

import annotations.Injectable;

public class LoggerSettings {
    private final String logPath;
    @Injectable
    private String loggingType;

    public LoggerSettings() {
        this.logPath = "Homework/domain/src/main/resources/application.properties";
    }

    public String getLogPath() {
        return logPath;
    }

    public String getLoggingType() {
        return loggingType;
    }
}
