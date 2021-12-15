package models;

import java.util.List;

public class Lection {
    private final String name;
    private String describe;
    private List<Resource> resources;
    private Person lecturer;
    private List<HomeWork> homeWork;

    public Lection(String name) {
        this.name = name;
    }

    public Lection(String name, List<Resource> resources) {
        this.name = name;
        this.resources = resources;
    }

    public Lection(String name, String describe, List<Resource> resources, Person lecturer,
                   List<HomeWork> homeWork) {
        this.name = name;
        this.describe = describe;
        this.resources = resources;
        this.lecturer = lecturer;
        this.homeWork = homeWork;
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

    public List<HomeWork> getHomeWork() {
        return homeWork;
    }
}
