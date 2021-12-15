package sources;

import models.Homework;

import java.util.ArrayList;
import java.util.List;

public class HomeworkSource {
    private static HomeworkSource instance;
    private final List<Homework> homeworks;

    public HomeworkSource() {
        this.homeworks = new ArrayList<>();
    }

    public List<Homework> getHomeworks() {
        return homeworks;
    }

    public void addHomework(Homework homework) {
        homeworks.add(homework);
    }

    public void deleteHomework(int id) {
        homeworks.remove(id);
    }

    public Homework getHomework(int id) {
        return homeworks.get(id);
    }

    public static HomeworkSource getInstance() {
        if (instance == null) {
            instance = new HomeworkSource();
        }

        return instance;
    }
}
