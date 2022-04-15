package services;

import models.HomeWork;
import repository.HomeWorkRepository;
import validators.HomeWorkValidator;

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

    public Optional<HomeWork> addHomeWork(HomeWork homeWork, int lectionId) {
        validator.validate(homeWork);
        homeWorkRepository.addHomeWork(homeWork, lectionId);
        return Optional.of(homeWork);
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
