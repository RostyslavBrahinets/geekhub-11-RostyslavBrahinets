package services;

import exceptions.NotFoundException;
import exceptions.ValidationException;
import logger.Logger;
import models.HomeWork;
import sources.HomeWorkSource;
import validators.HomeWorkValidator;

import java.util.List;

public class HomeWorkService {
    private final HomeWorkSource homeWorkSource = HomeWorkSource.getInstance();
    private final HomeWorkValidator validator = new HomeWorkValidator();

    public List<HomeWork> getHomeWorks() {
        return homeWorkSource.getHomeWorks();
    }

    public void addHomeWork(String task) {
        try {
            validator.validate(task);
            homeWorkSource.addHomeWork(new HomeWork(task));
        } catch (ValidationException e) {
            Logger.error(getClass().getName(), e.getMessage(), e);
        }
    }

    public void deleteHomeWork(int id) {
        try {
            validator.validate(id);
            homeWorkSource.deleteHomeWork(id);
        } catch (NotFoundException e) {
            Logger.error(getClass().getName(), e.getMessage(), e);
        }
    }

    public HomeWork getHomeWork(int id) {
        HomeWork homeWork = null;

        try {
            validator.validate(id);
            homeWork = homeWorkSource.getHomeWork(id);
        } catch (NotFoundException e) {
            Logger.error(getClass().getName(), e.getMessage(), e);
        }

        return homeWork;
    }
}
