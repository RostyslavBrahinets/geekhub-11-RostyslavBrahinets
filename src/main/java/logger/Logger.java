package logger;

import java.util.ArrayList;
import java.util.List;

public class Logger {
    private static final List<String> logs = new ArrayList<>();

    public static void info(String className, String message) {
        String log = "[" + LogType.INFO + "] " + className + ": " + message;
        logs.add(log);
        System.out.println(log);
    }

    public static void warning(String className, String message) {
        String log = "[" + LogType.WARNING + "] " + className + ": " + message;
        logs.add(log);
        System.out.println(log);
    }

    public static void error(String className, String message) {
        String log = "[" + LogType.ERROR + "] " + className + ": " + message;
        logs.add(log);
        System.out.println(log);
    }

    public static void error(String className, String message, Exception e) {
        String log = "[" + LogType.ERROR + "] " + className + ": " + message + "\n"
            + e.getStackTrace()[0];
        logs.add(log);
        System.out.println(log);
    }

    public static void showAllLogs() {
        for (String log : logs) {
            System.out.println(log);
        }
    }
}
