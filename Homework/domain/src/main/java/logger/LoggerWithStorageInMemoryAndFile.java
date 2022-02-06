package logger;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LoggerWithStorageInMemoryAndFile extends LoggerWithStorageInFile implements LoggerStorageDao {
    private static final List<Log> logs = new ArrayList<>();

    @Override
    public void info(String className, String message) {
        Log log = new Log(LogType.INFO, className, message, LocalDateTime.now());
        logs.add(log);
        writeLogToFile(log.toString());
        System.out.println(log);
    }

    @Override
    public void warning(String className, String message) {
        Log log = new Log(LogType.WARNING, className, message, LocalDateTime.now());
        logs.add(log);
        writeLogToFile(log.toString());
        System.out.println(log);
    }

    @Override
    public void error(String className, String message) {
        Log log = new Log(LogType.ERROR, className, message, LocalDateTime.now());
        logs.add(log);
        writeLogToFile(log.toString());
        System.out.println(log);
    }

    @Override
    public void error(String className, String message, Exception e) {
        Log log = new Log(LogType.ERROR, className, message, LocalDateTime.now(), e);
        logs.add(log);
        writeLogToFile(log.toString());
        System.out.println(log);
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
}
