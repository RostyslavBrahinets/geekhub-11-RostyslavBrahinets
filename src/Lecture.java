import java.time.LocalDate;
import java.util.Objects;

public class Lecture {
    private String title;
    private String describe;
    private LocalDate date;
    private Lecturer lecturer;
    private Homework homework;

    public Lecture(String title, String describe, LocalDate date, Lecturer lecturer, Homework homework) {
        this.title = title;
        this.describe = describe;
        this.date = date;
        this.lecturer = lecturer;
        this.homework = homework;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public Homework getHomework() {
        return homework;
    }

    public void setHomework(Homework homework) {
        this.homework = homework;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Lecture lecture = (Lecture) o;
        return Objects.equals(title, lecture.title)
            && Objects.equals(describe, lecture.describe)
            && Objects.equals(date, lecture.date)
            && Objects.equals(lecturer, lecture.lecturer)
            && Objects.equals(homework, lecture.homework);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, describe, date, lecturer, homework);
    }

    @Override
    public String toString() {
        return "Lecture{" +
            "title='" + title + '\'' +
            ", describe='" + describe + '\'' +
            ", date=" + date +
            ", lecturer=" + lecturer +
            ", homework=" + homework +
            '}';
    }
}
