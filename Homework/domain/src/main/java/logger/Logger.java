package logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Logger {
    private static final List<String> logs = new ArrayList<>();
    private static LocalDateTime localDateTime;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss:ms");

    private Logger() {
    }

    public static void info(String className, String message) {
        localDateTime = LocalDateTime.now();
        String log = "[" + LogType.INFO + "] " + className + ": " + message + "\n"
            + localDateTime.format(formatter) + "\n\n";
        logs.add(log);
        writeLogToFile(log);
        System.out.println(log);
    }

    public static void warning(String className, String message) {
        localDateTime = LocalDateTime.now();
        String log = "[" + LogType.WARNING + "] " + className + ": " + message + "\n"
            + localDateTime.format(formatter) + "\n\n";
        logs.add(log);
        writeLogToFile(log);
        System.out.println(log);
    }

    public static void error(String className, String message) {
        localDateTime = LocalDateTime.now();
        String log = "[" + LogType.ERROR + "] " + className + ": " + message + "\n"
            + localDateTime.format(formatter) + "\n\n";
        logs.add(log);
        writeLogToFile(log);
        System.out.println(log);
    }

    public static void error(String className, String message, Exception e) {
        localDateTime = LocalDateTime.now();
        String log = "[" + LogType.ERROR + "] " + className + ": " + message + "\n" + localDateTime.format(formatter)
            + "\n" + e.getStackTrace()[0] + "\n\n";
        logs.add(log);
        writeLogToFile(log);
        System.out.println(log);
    }

    public static void showAllLogs() {
        for (String log : logs) {
            System.out.println(log);
        }
    }

    private static void writeLogToFile(String log) {
        File file = new File("Homework/domain/src/main/resources/log.txt");
        try (FileOutputStream out = new FileOutputStream(file, true)) {
            out.write(log.getBytes());
        } catch (IOException e) {
            System.out.println(e.getMessage() + "\n" + e.getCause());
        }
    }
}
