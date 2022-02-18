package models;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public final class Lection implements Serializable {
    @Serial
    private static final long serialVersionUID = 3L;
    private int id;
    private String name;
    private String describe;
    private List<Resource> resources;
    private Person lecturer;
    private List<HomeWork> homeWorks;
    private LocalDate creationDate;

    public Lection() {
    }

    public Lection(
        String name,
        String describe,
        List<Resource> resources,
        Person lecturer,
        List<HomeWork> homeWorks,
        LocalDate creationDate
    ) {
        this.name = name;
        this.describe = describe;
        this.resources = resources;
        this.lecturer = lecturer;
        this.homeWorks = homeWorks;
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lection lection = (Lection) o;
        return Objects.equals(name, lection.name)
            && Objects.equals(describe, lection.describe)
            && Objects.equals(resources, lection.resources)
            && Objects.equals(lecturer, lection.lecturer)
            && Objects.equals(homeWorks, lection.homeWorks)
            && Objects.equals(creationDate, lection.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, describe, resources, lecturer, homeWorks, creationDate);
    }

    @Override
    public String toString() {
        return "Lection {"
            + "name: '" + name + '\''
            + ", describe: '" + describe + '\''
            + ", resources: " + resources
            + ", lecturer: " + lecturer
            + ", homeWorks: " + homeWorks
            + ", creationDate: " + creationDate
            + '}';
    }

    public int id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String describe() {
        return describe;
    }

    public List<Resource> resources() {
        return resources;
    }

    public Person lecturer() {
        return lecturer;
    }

    public List<HomeWork> homeWorks() {
        return homeWorks;
    }

    public LocalDate creationDate() {
        return creationDate;
    }

}
