package services;

import exceptions.NotFoundException;
import exceptions.ValidationException;
import logger.Logger;
import models.HomeWork;
import repository.HomeWorkRepository;
import validators.HomeWorkValidator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class HomeWorkService {
    private final HomeWorkRepository homeWorkSource = HomeWorkRepository.getInstance();
    private final HomeWorkValidator validator = new HomeWorkValidator();

    public Optional<List<HomeWork>> getHomeWorks() {
        return Optional.ofNullable(homeWorkSource.getHomeWorks());
    }

    public void addHomeWork(String task, LocalDateTime deadLine) {
        try {
            validator.validate(task);
            homeWorkSource.addHomeWork(new HomeWork(task, deadLine));
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

    public Optional<HomeWork> getHomeWork(int id) {
        HomeWork homeWork = null;

        try {
            validator.validate(id);
            homeWork = homeWorkSource.getHomeWork(id);
        } catch (NotFoundException e) {
            Logger.error(getClass().getName(), e.getMessage(), e);
        }

        return Optional.ofNullable(homeWork);
    }
}
