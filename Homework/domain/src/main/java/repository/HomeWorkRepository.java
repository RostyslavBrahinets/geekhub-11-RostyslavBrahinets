package repository;

import models.HomeWork;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HomeWorkRepository {
    private static HomeWorkRepository instance;
    private final List<HomeWork> homeWorks;

    public HomeWorkRepository() {
        this.homeWorks = new ArrayList<>();
    }

    public List<HomeWork> getHomeWorks() {
        return homeWorks;
    }

    public void addHomeWork(HomeWork homeWork) {
        homeWorks.add(homeWork);
    }

    public void deleteHomeWork(int id) {
        homeWorks.remove(id);
    }

    public Optional<HomeWork> getHomeWork(int id) {
        return Optional.ofNullable(homeWorks.get(id));
    }

    public static HomeWorkRepository getInstance() {
        if (instance == null) {
            instance = new HomeWorkRepository();
        }

        return instance;
    }
}
