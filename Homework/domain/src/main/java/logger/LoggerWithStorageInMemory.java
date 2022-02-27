package logger;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LoggerWithStorageInMemory implements LoggerStorageDao {
    private static final List<Log> logs = new ArrayList<>();

    @Override
    public void info(String className, String message) {
        Log log = new Log(LogType.INFO, className, message, LocalDateTime.now());
        logs.add(log);
        System.out.println(log);
    }

    @Override
    public void warning(String className, String message) {
        Log log = new Log(LogType.WARNING, className, message, LocalDateTime.now());
        logs.add(log);
        System.out.println(log);
    }

    @Override
    public void error(String className, String message) {
        Log log = new Log(LogType.ERROR, className, message, LocalDateTime.now());
        logs.add(log);
        System.out.println(log);
    }

    @Override
    public void error(String className, String message, Exception e) {
        Log log = new Log(LogType.ERROR, className, message, LocalDateTime.now(), e);
        logs.add(log);
        System.out.println(log);
    }

    @Override
    public List<String> getLogs() {
        return logs.stream().map(Log::toString).toList();
    }

    @Override
    public List<String> getSortedLogsByDateAsc() {
        return logs.stream()
            .sorted(Comparator.comparing(Log::getLocalDateTime))
            .toList().stream().map(Log::toString).toList();
    }

    @Override
    public List<String> getSortedLogsByDateDesc() {
        return logs.stream()
            .sorted(Comparator.comparing(Log::getLocalDateTime).reversed())
            .toList().stream().map(Log::toString).toList();
    }

    @Override
    public List<String> getLogsByStatus(LogType status) {
        return logs.stream()
            .filter(log -> log.getType() == status)
            .toList().stream().map(Log::toString).toList();
    }
}
