package services;

import exceptions.NotFoundException;
import exceptions.ValidationException;
import logger.Logger;
import models.Homework;
import sources.HomeworkSource;
import validators.HomeworkValidator;

import java.util.List;

public class HomeworkService {
    private final HomeworkSource homeworkSource = HomeworkSource.getInstance();
    private final HomeworkValidator validator = new HomeworkValidator();

    public List<Homework> getHomeworks() {
        return homeworkSource.getHomeworks();
    }

    public void addHomework(List<String> tasks) {
        try {
            validator.validate(tasks);
            homeworkSource.addHomework(new Homework(tasks));
        } catch (ValidationException e) {
            Logger.error(getClass().getName(), e.getMessage(), e);
        }
    }

    public void deleteHomework(int id) {
        try {
            validator.validate(id);
            homeworkSource.deleteHomework(id);
        } catch (NotFoundException e) {
            Logger.error(getClass().getName(), e.getMessage(), e);
        }
    }

    public Homework getHomework(int id) {
        Homework homework = null;

        try {
            validator.validate(id);
            homework = homeworkSource.getHomework(id);
        } catch (NotFoundException e) {
            Logger.error(getClass().getName(), e.getMessage(), e);
        }

        return homework;
    }
}
