package logger;

import java.io.*;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LoggerWithStorageInFile implements Logger {
    private LocalDateTime localDateTime;
    private static final String LOGS_FILE = String.valueOf(
        Paths.get(System.getProperty("user.home")).resolve("/logs.log")
    );

    @Override
    public void info(String className, String message) {
        localDateTime = LocalDateTime.now();
        Log log = new Log(LogType.INFO, className, message, localDateTime);
        writeLogToFile(log.toString());
        System.out.println(log);
    }

    @Override
    public void warning(String className, String message) {
        localDateTime = LocalDateTime.now();
        Log log = new Log(LogType.WARNING, className, message, localDateTime);
        writeLogToFile(log.toString());
        System.out.println(log);
    }

    @Override
    public void error(String className, String message) {
        localDateTime = LocalDateTime.now();
        Log log = new Log(LogType.ERROR, className, message, localDateTime);
        writeLogToFile(log.toString());
        System.out.println(log);
    }

    @Override
    public void error(String className, String message, Exception e) {
        localDateTime = LocalDateTime.now();
        Log log = new Log(LogType.ERROR, className, message, localDateTime, e);
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
        List<String> logs = getLogs();
        logs.forEach(System.out::println);
    }

    @Override
    public void showSortedLogsByDateDESC() {
        List<String> logs = getLogs();
        Collections.reverse(logs);
        logs.forEach(System.out::println);
    }

    @Override
    public void showLogsByStatus(LogType status) {
        List<String> logs = getLogs();
        logs.stream()
            .filter(log -> log.contains(status.toString()))
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

    private List<String> getLogs() {
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
