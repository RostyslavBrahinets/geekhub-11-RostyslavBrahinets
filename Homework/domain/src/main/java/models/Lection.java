package models;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Lection {
    private final String name;
    private final String describe;
    private final List<Resource> resources;
    private final Person lecturer;
    private final List<HomeWork> homeWorks;
    private final LocalDate creationDate;

    public Lection(String name, String describe, List<Resource> resources, Person lecturer,
                   List<HomeWork> homeWorks, LocalDate creationDate) {
        this.name = name;
        this.describe = describe;
        this.resources = resources;
        this.lecturer = lecturer;
        this.homeWorks = homeWorks;
        this.creationDate = creationDate;
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

    public LocalDate getCreationDate() {
        return creationDate;
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
}
