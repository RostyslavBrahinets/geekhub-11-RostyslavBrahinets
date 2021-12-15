package models;

import java.util.List;
import java.util.Objects;

public class Homework {
    private List<String> tasks;

    public Homework(List<String> tasks) {
        this.tasks = tasks;
    }

    public List<String> getTasks() {
        return tasks;
    }

    public void setTasks(List<String> tasks) {
        this.tasks = tasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Homework homework = (Homework) o;
        return Objects.equals(tasks, homework.tasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tasks);
    }

    @Override
    public String toString() {
        return String.format("Homework {%s}", tasks);
    }
}