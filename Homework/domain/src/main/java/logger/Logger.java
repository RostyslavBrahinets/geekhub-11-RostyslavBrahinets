package logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Logger {
    private static final List<Log> logs = new ArrayList<>();
    private static LocalDateTime localDateTime;

    private Logger() {
    }

    public static void info(String className, String message) {
        localDateTime = LocalDateTime.now();
        Log log = new Log(LogType.INFO, className, message, localDateTime);
        String logString = log.toString();
        logs.add(log);
        writeLogToFile(logString);
        System.out.println(logString);
    }

    public static void warning(String className, String message) {
        localDateTime = LocalDateTime.now();
        Log log = new Log(LogType.WARNING, className, message, localDateTime);
        String logString = log.toString();
        logs.add(log);
        writeLogToFile(logString);
        System.out.println(logString);
    }

    public static void error(String className, String message) {
        localDateTime = LocalDateTime.now();
        Log log = new Log(LogType.ERROR, className, message, localDateTime);
        String logString = log.toString();
        logs.add(log);
        writeLogToFile(logString);
        System.out.println(logString);
    }

    public static void error(String className, String message, Exception e) {
        localDateTime = LocalDateTime.now();
        Log log = new Log(LogType.ERROR, className, message, localDateTime, e);
        String logString = log.toString();
        logs.add(log);
        writeLogToFile(logString);
        System.out.println(logString);
    }

    public static void showAllLogs() {
        for (Log log : logs) {
            System.out.println(log);
        }
    }

    public static void showLogsFromFile() {
        File file = new File("Homework/domain/src/main/resources/logs.log");
        try (FileInputStream in = new FileInputStream(file)) {
            int readBytes;
            while ((readBytes = in.read()) != -1) {
                System.out.print((char) readBytes);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage() + "\n" + e.getCause());
        }
    }

    private static void writeLogToFile(String log) {
        File file = new File("Homework/domain/src/main/resources/logs.log");
        try (FileOutputStream out = new FileOutputStream(file, true)) {
            out.write(log.getBytes());
        } catch (IOException e) {
            System.out.println(e.getMessage() + "\n" + e.getCause());
        }
    }
}
