package services;

import models.HomeWork;
import repository.HomeWorkRepository;
import validators.HomeWorkValidator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class HomeWorkService {
    private final HomeWorkRepository homeWorkRepository;
    private final HomeWorkValidator validator;

    public HomeWorkService(
        HomeWorkRepository homeWorkRepository,
        HomeWorkValidator validator
    ) {
        this.homeWorkRepository = homeWorkRepository;
        this.validator = validator;
    }

    public List<HomeWork> getHomeWorks() {
        return homeWorkRepository.getHomeWorks();
    }

    public void addHomeWork(String task, LocalDateTime deadline, int lectionId) {
        validator.validate(task, deadline);
        homeWorkRepository.addHomeWork(new HomeWork(task, deadline), lectionId);
    }

    public void deleteHomeWork(int id) {
        validator.validate(id);
        homeWorkRepository.deleteHomeWork(id);
    }

    public Optional<HomeWork> getHomeWork(int id) {
        validator.validate(id);
        return homeWorkRepository.getHomeWork(id);
    }
}
