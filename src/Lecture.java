import java.time.LocalDate;
import java.util.Objects;

public class Lecture {
    private String title;
    private String describe;
    private Lecturer lecturer;
    private Homework homework;
    private AdditionalMaterial additionalMaterial;

    public Lecture(String title, String describe, Lecturer lecturer,
                   Homework homework, AdditionalMaterial additionalMaterial) {
        this.title = title;
        this.describe = describe;
        this.lecturer = lecturer;
        this.homework = homework;
        this.additionalMaterial = additionalMaterial;
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

    public AdditionalMaterial getAdditionalMaterial() {
        return additionalMaterial;
    }

    public void setAdditionalMaterial(AdditionalMaterial additionalMaterial) {
        this.additionalMaterial = additionalMaterial;
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
            && Objects.equals(lecturer, lecture.lecturer)
            && Objects.equals(homework, lecture.homework)
            && Objects.equals(additionalMaterial, lecture.additionalMaterial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, describe, lecturer, homework, additionalMaterial);
    }

    @Override
    public String toString() {
        return "Lecture{"
            + "title='" + title + '\''
            + ", describe='" + describe + '\''
            + ", lecturer=" + lecturer
            + ", homework=" + homework
            + ", additionalMaterial=" + additionalMaterial
            + '}';
    }
}
