package logger;

public class Logger {
    private static String[] logs = new String[0];

    public static void info(String className, String message) {
        String log = "[" + LogType.INFO + "] " + className + ": " + message;
        addLog(log);
        System.out.println(log);
    }

    public static void warning(String className, String message) {
        String log = "[" + LogType.WARNING + "] " + className + ": " + message;
        addLog(log);
        System.out.println(log);
    }

    public static void error(String className, String message) {
        String log = "[" + LogType.ERROR + "] " + className + ": " + message;
        addLog(log);
        System.out.println(log);
    }

    public static void error(String className, String message, Exception e) {
        String log = "[" + LogType.ERROR + "] " + className + ": " + message + "\n"
            + e.getStackTrace()[0];
        addLog(log);
        System.out.println(log);
    }

    public static void showAllLogs() {
        for (String log : logs) {
            System.out.println(log);
        }
    }

    private static void addLog(String log) {
        String[] newLogs = new String[logs.length + 1];

        for (int i = 0; i < logs.length; i++) {
            newLogs[i] = logs[i];
        }

        newLogs[logs.length] = log;

        logs = newLogs;
    }
}
