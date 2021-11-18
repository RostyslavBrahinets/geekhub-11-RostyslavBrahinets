import java.time.LocalDateTime;
import java.util.Objects;

public class Homework {
    private String task;
    private LocalDateTime deadline;
    private Lecturer lecturer;

    public Homework(String task, LocalDateTime deadline, Lecturer lecturer) {
        this.task = task;
        this.deadline = deadline;
        this.lecturer = lecturer;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
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

        Homework homework = (Homework) o;
        return Objects.equals(task, homework.task)
            && Objects.equals(deadline, homework.deadline)
            && Objects.equals(lecturer, homework.lecturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(task, deadline, lecturer);
    }

    @Override
    public String toString() {
        return "Homework{" +
            "task='" + task + '\'' +
            ", deadline=" + deadline +
            ", lecturer=" + lecturer +
            '}';
    }
}