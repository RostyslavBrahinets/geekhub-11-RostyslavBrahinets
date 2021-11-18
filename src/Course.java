import java.util.Arrays;
import java.util.Objects;

public class Course {
    private String name;
    private Lecture[] lectures;
    private Lecturer[] lecturers;
    private Student[] students;
    private String formOfStudy;
    private String address;

    public Course(String name, Lecture[] lectures, Lecturer[] lecturers, Student[] students,
                  String formOfStudy, String address) {
        this.name = name;
        this.lectures = lectures;
        this.lecturers = lecturers;
        this.students = students;
        this.formOfStudy = formOfStudy;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Lecture[] getLectures() {
        return lectures;
    }

    public void setLectures(Lecture[] lectures) {
        this.lectures = lectures;
    }

    public Lecturer[] getLecturers() {
        return lecturers;
    }

    public void setLecturers(Lecturer[] lecturers) {
        this.lecturers = lecturers;
    }

    public Student[] getStudents() {
        return students;
    }

    public void setStudents(Student[] students) {
        this.students = students;
    }

    public String getFormOfStudy() {
        return formOfStudy;
    }

    public void setFormOfStudy(String formOfStudy) {
        this.formOfStudy = formOfStudy;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Course course = (Course) o;
        return Objects.equals(name, course.name)
            && Arrays.equals(lectures, course.lectures)
            && Arrays.equals(lecturers, course.lecturers)
            && Arrays.equals(students, course.students)
            && Objects.equals(formOfStudy, course.formOfStudy)
            && Objects.equals(address, course.address);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, formOfStudy, address);
        result = 31 * result + Arrays.hashCode(lectures);
        result = 31 * result + Arrays.hashCode(lecturers);
        result = 31 * result + Arrays.hashCode(students);
        return result;
    }

    @Override
    public String toString() {
        return "Course{"
            + "name='" + name + '\''
            + ", lectures=" + Arrays.toString(lectures)
            + ", lecturers=" + Arrays.toString(lecturers)
            + ", students=" + Arrays.toString(students)
            + ", formOfStudy='" + formOfStudy + '\''
            + ", address='" + address + '\''
            + '}';
    }
}