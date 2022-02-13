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
    public List<Log> getLogs() {
        return readLogsFromFile();
    }

    @Override
    public List<Log> getSortedLogsByDateAsc() {
        List<Log> logs = readLogsFromFile();
        return logs.stream()
            .sorted(Comparator.comparing(Log::getLocalDateTime))
            .toList();
    }

    @Override
    public List<Log> getSortedLogsByDateDesc() {
        List<Log> logs = readLogsFromFile();
        return logs.stream()
            .sorted(Comparator.comparing(Log::getLocalDateTime).reversed())
            .toList();
    }

    @Override
    public List<Log> getLogsByStatus(LogType status) {
        List<Log> logs = readLogsFromFile();
        return logs.stream()
            .filter(log -> log.getType() == status)
            .toList();
    }

    protected void writeLogToFile(Log log) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(LOGS_FILE, true);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(log);
        } catch (IOException e) {
//            System.out.println(e.getMessage() + "\n" + e.getCause());
            e.printStackTrace();
        }
    }

    private List<Log> readLogsFromFile() {
        List<Log> logs = new ArrayList<>();

        try {
            FileInputStream fileInputStream = new FileInputStream(LOGS_FILE);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            while (true) {
                Object object = objectInputStream.readObject();
                if (object == null) {
                    break;
                }

                Log log = (Log) object;
                logs.add(log);
            }
        } catch (EOFException ignored) {
        } catch (IOException | ClassNotFoundException e) {
//            System.out.println(e.getMessage() + "\n" + e.getCause());
            e.printStackTrace();
        }

        return logs;
    }
}
