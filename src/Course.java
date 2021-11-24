public class Course {
    private final String name;
    private final Lection[] lections;
    private final Person[] students;

    public Course(String name, Lection[] lections, Person[] students) {
        this.name = name;
        this.lections = lections;
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public Lection[] getLections() {
        return lections;
    }

    public Person[] getStudents() {
        return students;
    }
}