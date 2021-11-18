import java.time.LocalDate;
import java.util.Objects;

public class Lecture {
    private String title;
    private String describe;
    private LocalDate date;
    private Lecturer lecturer;

    public Lecture(String title, String describe, LocalDate date, Lecturer lecturer) {
        this.title = title;
        this.describe = describe;
        this.date = date;
        this.lecturer = lecturer;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Lecture lecture = (Lecture) o;
        return Objects.equals(title, lecture.title) && Objects.equals(describe, lecture.describe) && Objects.equals(date, lecture.date) && Objects.equals(lecturer, lecture.lecturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, describe, date, lecturer);
    }

    @Override
    public String toString() {
        return "Lecture{" +
            "title='" + title + '\'' +
            ", describe='" + describe + '\'' +
            ", date=" + date +
            ", lecturer=" + lecturer +
            '}';
    }
}
