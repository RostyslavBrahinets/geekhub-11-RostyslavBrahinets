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
    private LocalDateTime deadline;

    public HomeWork() {
    }

    public HomeWork(
        String task,
        LocalDateTime deadline
    ) {
        this.task = task;
        this.deadline = deadline;
    }

    public HomeWork(
        int id,
        String task,
        LocalDateTime deadline
    ) {
        this.id = id;
        this.task = task;
        this.deadline = deadline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HomeWork homeWork = (HomeWork) o;
        return Objects.equals(task, homeWork.task) && Objects.equals(deadline, homeWork.deadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(task, deadline);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return "HomeWork {"
            + "id: '" + id + "'\t"
            + "task: '" + task + "'\t"
            + "deadLine: '" + deadline.format(formatter) + "'"
            + '}';
    }

    public int id() {
        return id;
    }

    public String task() {
        return task;
    }

    public LocalDateTime deadline() {
        return deadline;
    }
}