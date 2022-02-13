package models;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public record Course(
    String name,
    List<Lection> lections,
    List<Person> students
) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

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
}