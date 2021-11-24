package logger;

public class Logger {
    private static String[] logs = new String[0];

    public static void log(LogType type, String className, String message) {
        String log = "[" + type + "] " + className + ": " + message;
        addLog(log);
        System.out.println(log);
    }

    public static void log(LogType type, String className, String message, Exception e) {
        String log = "[" + type + "] " + className + ": " + message + "\n" + e.getStackTrace()[0];
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
