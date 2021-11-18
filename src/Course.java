import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Objects;

public class Course {
    private String name;
    private Lecture[] lectures;
    private Lecturer[] lecturers;
    private Student[] students;
    private String formOfStudy;
    private String address;
    private LocalDate startDate;
    private LocalDate finishDate;
    private LocalTime time;

    public Course(String name, Lecture[] lectures, Lecturer[] lecturers, Student[] students,
                  String formOfStudy, String address, LocalDate startDate, LocalDate finishDate,
                  LocalTime time) {
        this.name = name;
        this.lectures = lectures;
        this.lecturers = lecturers;
        this.students = students;
        this.formOfStudy = formOfStudy;
        this.address = address;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.time = time;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
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
            && Objects.equals(address, course.address)
            && Objects.equals(startDate, course.startDate)
            && Objects.equals(finishDate, course.finishDate)
            && Objects.equals(time, course.time);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, formOfStudy, address, startDate, finishDate, time);
        result = 31 * result + Arrays.hashCode(lectures);
        result = 31 * result + Arrays.hashCode(lecturers);
        result = 31 * result + Arrays.hashCode(students);
        return result;
    }
}