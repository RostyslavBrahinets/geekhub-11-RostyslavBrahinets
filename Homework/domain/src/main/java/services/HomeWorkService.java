package services;

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
        validator.validate(task);
        homeWorkSource.addHomeWork(new HomeWork(task, deadLine));
    }

    public void deleteHomeWork(int id) {
        validator.validate(id);
        homeWorkSource.deleteHomeWork(id);
    }

    public Optional<HomeWork> getHomeWork(int id) {
        validator.validate(id);
        return Optional.ofNullable(homeWorkSource.getHomeWork(id));
    }
}
