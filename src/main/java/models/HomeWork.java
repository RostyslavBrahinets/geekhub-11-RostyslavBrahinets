package models;

import java.util.Objects;

public class HomeWork {
    private final String task;

    public HomeWork(String task) {
        this.task = task;
    }

    public String getTask() {
        return task;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HomeWork homeWork = (HomeWork) o;
        return Objects.equals(task, homeWork.task);
    }

    @Override
    public int hashCode() {
        return Objects.hash(task);
    }

    @Override
    public String toString() {
        return "HomeWork {task: '" + task + "'}";
    }
}