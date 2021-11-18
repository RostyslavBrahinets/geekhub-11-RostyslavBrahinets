import java.util.Objects;

public class Homework {
    private String task;

    public Homework(String task) {
        this.task = task;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
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
        return Objects.equals(task, homework.task);
    }

    @Override
    public int hashCode() {
        return Objects.hash(task);
    }

    @Override
    public String toString() {
        return "Homework{"
            + "task='" + task + '\''
            + '}';
    }
}