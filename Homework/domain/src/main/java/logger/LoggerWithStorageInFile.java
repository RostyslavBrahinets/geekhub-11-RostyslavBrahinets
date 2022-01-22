package logger;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class LoggerWithStorageInFile implements Logger {
    private LocalDateTime localDateTime;
    private static final String LOGS_FILE = "Homework/domain/src/main/resources/logs.log";

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

    }

    @Override
    public void showSortedLogsByDateDESC() {

    }

    @Override
    public void showLogsByStatus(LogType status) {
        ArrayList<String> logs = new ArrayList<>();
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
}
