package logger;

import java.io.*;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LoggerWithStorageInFile implements LoggerStorageDao {
    private static final String LOGS_FILE = String.valueOf(
        Paths.get(System.getProperty("user.home")).resolve("/logs.log")
    );

    @Override
    public void info(String className, String message) {
        Log log = new Log(LogType.INFO, className, message, LocalDateTime.now());
        writeLogToFile(log.toString());
        System.out.println(log);
    }

    @Override
    public void warning(String className, String message) {
        Log log = new Log(LogType.WARNING, className, message, LocalDateTime.now());
        writeLogToFile(log.toString());
        System.out.println(log);
    }

    @Override
    public void error(String className, String message) {
        Log log = new Log(LogType.ERROR, className, message, LocalDateTime.now());
        writeLogToFile(log.toString());
        System.out.println(log);
    }

    @Override
    public void error(String className, String message, Exception e) {
        Log log = new Log(LogType.ERROR, className, message, LocalDateTime.now(), e);
        writeLogToFile(log.toString());
        System.out.println(log);
    }

    @Override
    public List<Log> getLogs() {
        File file = new File(LOGS_FILE);
        try (FileInputStream in = new FileInputStream(file)) {
            int readBytes;
            while ((readBytes = in.read()) != -1) {
                System.out.print((char) readBytes);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage() + "\n" + e.getCause());
        }

        return List.of();
    }

    @Override
    public List<Log> getSortedLogsByDateAsc() {
        List<String> logs = getLogsFromFile();
        return List.of();
    }

    @Override
    public List<Log> getSortedLogsByDateDesc() {
        List<String> logs = getLogsFromFile();
        Collections.reverse(logs);
        return List.of();
    }

    @Override
    public List<Log> getLogsByStatus(LogType status) {
        List<String> logs = getLogsFromFile();
        logs = logs.stream()
            .filter(log -> log.contains(status.toString()))
            .toList();

        return List.of();
    }

    protected void writeLogToFile(String log) {
        File file = new File(LOGS_FILE);
        try (FileOutputStream out = new FileOutputStream(file, true)) {
            out.write(log.getBytes());
        } catch (IOException e) {
            System.out.println(e.getMessage() + "\n" + e.getCause());
        }
    }

    private List<String> getLogsFromFile() {
        List<String> logs = new ArrayList<>();
        File file = new File(LOGS_FILE);

        try (FileReader reader = new FileReader(file)) {
            StringBuilder stringBuilder = new StringBuilder();
            while (reader.ready()) {
                char c = (char) reader.read();
                if (c == '\n' && reader.read() == '\n') {
                    logs.add(stringBuilder.toString());
                    stringBuilder = new StringBuilder();
                } else {
                    stringBuilder.append(c);
                }
            }
            if (stringBuilder.length() > 0) {
                logs.add(stringBuilder.toString());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage() + "\n" + e.getCause());
        }

        return logs;
    }
}
