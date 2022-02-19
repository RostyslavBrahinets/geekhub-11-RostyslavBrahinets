package models;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public final class Course implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private List<Lection> lections;
    private List<Person> students;

    public Course() {
    }

    public Course(
        String name,
        List<Lection> lections,
        List<Person> students
    ) {
        this.name = name;
        this.lections = lections;
        this.students = students;
    }

    public Course(
        int id,
        String name,
        List<Lection> lections,
        List<Person> students
    ) {
        this.id = id;
        this.name = name;
        this.lections = lections;
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(name, course.name)
            && Objects.equals(lections, course.lections)
            && Objects.equals(students, course.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lections, students);
    }

    @Override
    public String toString() {
        return "Course {"
            + "name: '" + name + '\''
            + ", lections: " + lections
            + ", students: " + students
            + '}';
    }

    public int id() {
        return id;
    }

    public String name() {
        return name;
    }

    public List<Lection> lections() {
        return lections;
    }

    public List<Person> students() {
        return students;
    }

}