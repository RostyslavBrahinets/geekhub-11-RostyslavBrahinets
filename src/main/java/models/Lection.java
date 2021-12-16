package models;

import java.util.List;

public class Lection {
    private final String name;
    private final String describe;
    private final List<Resource> resources;
    private final Person lecturer;
    private final List<HomeWork> homeWorks;

    public Lection(String name, String describe, List<Resource> resources, Person lecturer,
                   List<HomeWork> homeWorks) {
        this.name = name;
        this.describe = describe;
        this.resources = resources;
        this.lecturer = lecturer;
        this.homeWorks = homeWorks;
    }

    public String getName() {
        return name;
    }

    public String getDescribe() {
        return describe;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public Person getLecturer() {
        return lecturer;
    }

    public List<HomeWork> getHomeWorks() {
        return homeWorks;
    }
}
