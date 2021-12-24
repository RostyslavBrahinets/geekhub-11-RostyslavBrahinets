package logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Logger {
    private static final List<String> logs = new ArrayList<>();
    private static LocalDateTime localDateTime;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss:ms");

    private Logger() {
        throw new IllegalStateException("Utility class");
    }

    public static void info(String className, String message) {
        localDateTime = LocalDateTime.now();
        String log = "[" + LogType.INFO + "] " + className + ": " + message + "\n" + localDateTime.format(formatter);
        logs.add(log);
        System.out.println(log);
    }

    public static void warning(String className, String message) {
        localDateTime = LocalDateTime.now();
        String log = "[" + LogType.WARNING + "] " + className + ": " + message + "\n" + localDateTime.format(formatter);
        logs.add(log);
        System.out.println(log);
    }

    public static void error(String className, String message) {
        localDateTime = LocalDateTime.now();
        String log = "[" + LogType.ERROR + "] " + className + ": " + message + "\n" + localDateTime.format(formatter);
        logs.add(log);
        System.out.println(log);
    }

    public static void error(String className, String message, Exception e) {
        localDateTime = LocalDateTime.now();
        String log = "[" + LogType.ERROR + "] " + className + ": " + message + "\n" + localDateTime.format(formatter)
            + "\n" + e.getStackTrace()[0];
        logs.add(log);
        System.out.println(log);
    }

    public static void showAllLogs() {
        for (String log : logs) {
            System.out.println(log);
        }
    }
}
