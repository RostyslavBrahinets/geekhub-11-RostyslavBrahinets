package models;

public class Lection {
    private final String name;
    private String describe;
    private Resource[] resources;
    private Person lecturer;
    private HomeWork[] homeWork;

    public Lection(String name) {
        this.name = name;
    }

    public Lection(String name, Resource[] resources) {
        this.name = name;
        this.resources = resources;
    }

    public Lection(String name, String describe, Resource[] resources, Person lecturer, HomeWork[] homeWork) {
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

    public Resource[] getResources() {
        return resources;
    }

    public Person getLecturer() {
        return lecturer;
    }

    public HomeWork[] getHomeWork() {
        return homeWork;
    }
}
