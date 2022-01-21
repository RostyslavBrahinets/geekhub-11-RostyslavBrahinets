package models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public record HomeWork(
    String task,
    LocalDateTime deadLine
) implements Serializable {
    private static final int serialVersionUID = 2;

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
}