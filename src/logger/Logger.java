package logger;

public class Logger {
    public static void log(LogType type, String className, String message) {
        System.out.printf("%n[%s] %s: %s%n", type, className, message);
    }

    public static void log(LogType type, String className, String message, Exception e) {
        System.out.printf("%n[%s] %s: %s%n%s%n", type, className, message, e.getStackTrace()[0]);
    }
}
