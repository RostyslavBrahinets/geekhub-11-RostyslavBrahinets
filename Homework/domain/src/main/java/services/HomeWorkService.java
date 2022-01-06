package services;

import models.HomeWork;
import repository.HomeWorkRepository;
import validators.HomeWorkValidator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class HomeWorkService {
    private final HomeWorkRepository homeWorkRepository = HomeWorkRepository.getInstance();
    private final HomeWorkValidator validator = new HomeWorkValidator();

    public Optional<List<HomeWork>> getHomeWorks() {
        return Optional.ofNullable(homeWorkRepository.getHomeWorks());
    }

    public void addHomeWork(String task, LocalDateTime deadline) {
        validator.validate(task, deadline);
        homeWorkRepository.addHomeWork(new HomeWork(task, deadline));
    }

    public void deleteHomeWork(int id) {
        validator.validate(id);
        homeWorkRepository.deleteHomeWork(id);
    }

    public Optional<HomeWork> getHomeWork(int id) {
        validator.validate(id);
        return Optional.ofNullable(homeWorkRepository.getHomeWork(id));
    }
}
