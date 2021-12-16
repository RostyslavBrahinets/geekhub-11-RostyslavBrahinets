package sources;

import models.HomeWork;

import java.util.ArrayList;
import java.util.List;

public class HomeWorkSource {
    private static HomeWorkSource instance;
    private final List<HomeWork> homeWorks;

    public HomeWorkSource() {
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

    public HomeWork getHomeWork(int id) {
        return homeWorks.get(id);
    }

    public static HomeWorkSource getInstance() {
        if (instance == null) {
            instance = new HomeWorkSource();
        }

        return instance;
    }
}
