package models;

import java.util.List;

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
}