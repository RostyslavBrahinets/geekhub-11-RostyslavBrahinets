package logger;

import java.io.*;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LoggerWithStorageInFile implements LoggerStorageDao {
    private static final String LOGS_FILE = String.valueOf(
        Paths.get(System.getProperty("user.home")).resolve("/logs.log")
    );

    @Override
    public void info(String className, String message) {
        Log log = new Log(LogType.INFO, className, message, LocalDateTime.now());
        writeLogToFile(log);
        System.out.println(log);
    }

    @Override
    public void warning(String className, String message) {
        Log log = new Log(LogType.WARNING, className, message, LocalDateTime.now());
        writeLogToFile(log);
        System.out.println(log);
    }

    @Override
    public void error(String className, String message) {
        Log log = new Log(LogType.ERROR, className, message, LocalDateTime.now());
        writeLogToFile(log);
        System.out.println(log);
    }

    @Override
    public void error(String className, String message, Exception e) {
        Log log = new Log(LogType.ERROR, className, message, LocalDateTime.now(), e);
        writeLogToFile(log);
        System.out.println(log);
    }

    @Override
    public List<String> getLogs() {
        return readLogsFromFile();
    }

    @Override
    public List<String> getSortedLogsByDateAsc() {
        return getSortedLogs();
    }

    @Override
    public List<String> getSortedLogsByDateDesc() {
        List<String> sortedLogs = getSortedLogs();
        List<String> reversedLogs = new ArrayList<>();

        int length = sortedLogs.size() - 1;
        for (int i = length; i >= 0; i--) {
            reversedLogs.add(sortedLogs.get(i));
            sortedLogs.remove(i);
        }

        return reversedLogs;
    }

    @Override
    public List<String> getLogsByStatus(LogType status) {
        List<String> logs = readLogsFromFile();
        return logs.stream()
            .filter(log -> log.toUpperCase().contains(status.toString().toUpperCase()))
            .toList();
    }

    protected void writeLogToFile(Log log) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOGS_FILE, true))) {
            String str = log.toString() + "\n";
            writer.write(str);
        } catch (IOException e) {
            System.out.println(
                getClass().getName() + " " + e.getMessage() + " " + e.getCause()
            );
        }
    }

    private List<String> readLogsFromFile() {
        List<String> logs = new ArrayList<>();

        try (
            BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(LOGS_FILE))
            )
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                logs.add(line);
            }
        } catch (IOException e) {
            System.out.println(
                getClass().getName() + " " + e.getMessage() + " " + e.getCause()
            );
        }

        return logs;
    }

    private List<String> getSortedLogs() {
        List<String> logs = readLogsFromFile();
        List<List<String>> splitLogs = new ArrayList<>();

        for (String log : logs) {
            List<String> splitLog = List.of(log.split(" "));
            splitLogs.add(splitLog);
        }

        List<List<String>> sortedSplitLogs = splitLogs.stream().sorted(
            Comparator.comparing((log) -> LocalDateTime.parse(
                log.get(log.size() - 2) + "T" + log.get(log.size() - 1)
            ))
        ).toList();

        List<String> sortedLogs = new ArrayList<>();
        for (List<String> sortedSplitLog : sortedSplitLogs) {
            StringBuilder s = new StringBuilder();
            for (String log : sortedSplitLog) {
                s.append(log).append(" ");
            }
            sortedLogs.add(s.toString());
        }

        return sortedLogs;
    }
}
