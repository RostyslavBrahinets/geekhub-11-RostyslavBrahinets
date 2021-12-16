package models;

import java.util.List;

public class Course {
    private final String name;
    private final List<Lection> lections;
    private final List<Person> students;

    public Course(String name, List<Lection> lections, List<Person> students) {
        this.name = name;
        this.lections = lections;
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public List<Lection> getLections() {
        return lections;
    }

    public List<Person> getStudents() {
        return students;
    }

    @Override
    public String toString() {
        return "Course {"
            + "name: '" + name + '\''
            + ", lections: " + lections
            + ", students: " + students
            + '}';
    }
}