package logger;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LoggerWithStorageInMemory implements Logger {
    private static final List<Log> logs = new ArrayList<>();
    private LocalDateTime localDateTime;

    @Override
    public void info(String className, String message) {
        localDateTime = LocalDateTime.now();
        Log log = new Log(LogType.INFO, className, message, localDateTime);
        logs.add(log);
        System.out.println(log);
    }

    @Override
    public void warning(String className, String message) {
        localDateTime = LocalDateTime.now();
        Log log = new Log(LogType.WARNING, className, message, localDateTime);
        logs.add(log);
        System.out.println(log);
    }

    @Override
    public void error(String className, String message) {
        localDateTime = LocalDateTime.now();
        Log log = new Log(LogType.ERROR, className, message, localDateTime);
        logs.add(log);
        System.out.println(log);
    }

    @Override
    public void error(String className, String message, Exception e) {
        localDateTime = LocalDateTime.now();
        Log log = new Log(LogType.ERROR, className, message, localDateTime, e);
        logs.add(log);
        System.out.println(log);
    }

    @Override
    public void showLogs() {
        logs.forEach(System.out::println);
    }

    @Override
    public void showSortedLogsByDateASC() {
        List<Log> sortedLogs;
        sortedLogs = logs.stream()
            .sorted(Comparator.comparing(Log::getLocalDateTime))
            .toList();
        sortedLogs.forEach(System.out::println);
    }

    @Override
    public void showSortedLogsByDateDESC() {
        List<Log> sortedLogs;
        sortedLogs = logs.stream()
            .sorted(Comparator.comparing(Log::getLocalDateTime).reversed())
            .toList();
        sortedLogs.forEach(System.out::println);
    }
}
