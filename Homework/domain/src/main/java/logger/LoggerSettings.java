package logger;

import annotations.Injectable;

public class LoggerSettings {
    private final String propertiesPath;
    @Injectable
    private String loggingType;

    public LoggerSettings() {
        this.propertiesPath =
            "C:/Users/Asus-K54C/IdeaProjects/geekhub-11-RostyslavBrahinets/"
                + "Homework/domain/src/main/resources/application.properties";
    }

    public String getPropertiesPath() {
        return propertiesPath;
    }

    public String getLoggingType() {
        return loggingType;
    }
}
