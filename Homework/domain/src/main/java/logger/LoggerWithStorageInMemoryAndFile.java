package logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LoggerWithStorageInMemoryAndFile implements Logger {
    private LocalDateTime localDateTime;
    private static final List<Log> logs = new ArrayList<>();
    private static final String LOGS_FILE = String.valueOf(
        Paths.get(System.getProperty("user.home")).resolve("/logs.log")
    );

    @Override
    public void info(String className, String message) {
        localDateTime = LocalDateTime.now();
        Log log = new Log(LogType.INFO, className, message, localDateTime);
        logs.add(log);
        writeLogToFile(log.toString());
        System.out.println(log);
    }

    @Override
    public void warning(String className, String message) {
        localDateTime = LocalDateTime.now();
        Log log = new Log(LogType.WARNING, className, message, localDateTime);
        logs.add(log);
        writeLogToFile(log.toString());
        System.out.println(log);
    }

    @Override
    public void error(String className, String message) {
        localDateTime = LocalDateTime.now();
        Log log = new Log(LogType.ERROR, className, message, localDateTime);
        logs.add(log);
        writeLogToFile(log.toString());
        System.out.println(log);
    }

    @Override
    public void error(String className, String message, Exception e) {
        localDateTime = LocalDateTime.now();
        Log log = new Log(LogType.ERROR, className, message, localDateTime, e);
        logs.add(log);
        writeLogToFile(log.toString());
        System.out.println(log);
    }

    @Override
    public void showLogs() {
        File file = new File(LOGS_FILE);
        try (FileInputStream in = new FileInputStream(file)) {
            int readBytes;
            while ((readBytes = in.read()) != -1) {
                System.out.print((char) readBytes);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage() + "\n" + e.getCause());
        }
    }

    @Override
    public void showSortedLogsByDateASC() {
        logs.stream()
            .sorted(Comparator.comparing(Log::getLocalDateTime))
            .toList()
            .forEach(System.out::println);
    }

    @Override
    public void showSortedLogsByDateDESC() {
        logs.stream()
            .sorted(Comparator.comparing(Log::getLocalDateTime).reversed())
            .toList()
            .forEach(System.out::println);
    }

    @Override
    public void showLogsByStatus(LogType status) {
        logs.stream()
            .filter(log -> log.getType() == status)
            .toList()
            .forEach(System.out::println);
    }

    private void writeLogToFile(String log) {
        File file = new File(LOGS_FILE);
        try (FileOutputStream out = new FileOutputStream(file, true)) {
            out.write(log.getBytes());
        } catch (IOException e) {
            System.out.println(e.getMessage() + "\n" + e.getCause());
        }
    }
}
