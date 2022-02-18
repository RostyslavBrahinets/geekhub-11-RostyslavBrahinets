package models;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public final class HomeWork implements Serializable {
    @Serial
    private static final long serialVersionUID = 2L;
    private int id;
    private String task;
    private LocalDateTime deadLine;

    public HomeWork() {
    }

    public HomeWork(
        String task,
        LocalDateTime deadLine
    ) {
        this.task = task;
        this.deadLine = deadLine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HomeWork homeWork = (HomeWork) o;
        return Objects.equals(task, homeWork.task) && Objects.equals(deadLine, homeWork.deadLine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(task, deadLine);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return "HomeWork {"
            + "task: '" + task + '\''
            + "deadLine: '" + deadLine.format(formatter)
            + '}';
    }

    public int id() {
        return id;
    }

    public String task() {
        return task;
    }

    public LocalDateTime deadLine() {
        return deadLine;
    }

}