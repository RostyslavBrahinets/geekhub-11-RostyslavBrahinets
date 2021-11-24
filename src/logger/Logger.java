package logger;

public class Logger {
    public static void log(String message, LogType type, String className) {
        if (type == LogType.INFO) {
            System.out.printf("%n[INFO] ");
        } else if (type == LogType.WARNING) {
            System.out.printf("%n[WARNING] ");
        } else if (type == LogType.ERROR) {
            System.out.printf("%n[ERROR] ");
        }

        System.out.println(className + ": " + message);
    }

    public static void log(String message, LogType type, String className, Exception e) {
        if (type == LogType.ERROR) {
            System.out.printf("%n[ERROR] %s: %s%n%s%n", className, message, e.getStackTrace()[0]);
        }
    }
}
