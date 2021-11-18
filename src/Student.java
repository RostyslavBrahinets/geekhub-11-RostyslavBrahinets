import java.util.Arrays;
import java.util.Objects;

public class Student extends Person {
    private String repository;
    private int[] marks;

    public Student(String firstName, String lastName, String slackNickname, String gitHubNickname,
                   String repository, int[] marks) {
        super(firstName, lastName, slackNickname, gitHubNickname);
        this.repository = repository;
        this.marks = marks;
    }

    public int[] getMarks() {
        return marks;
    }

    public void setMarks(int[] marks) {
        this.marks = marks;
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Student student = (Student) o;
        return Objects.equals(getFirstName(), student.getFirstName())
            && Objects.equals(getLastName(), student.getLastName())
            && Objects.equals(getSlackNickname(), student.getSlackNickname())
            && Objects.equals(getGitHubNickname(), student.getGitHubNickname())
            && Objects.equals(repository, student.repository)
            && Arrays.equals(marks, student.marks);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getFirstName(), getLastName(), getSlackNickname(),
            getGitHubNickname(), repository);
        result = 31 * result + Arrays.hashCode(marks);
        return result;
    }

    @Override
    public String toString() {
        return "Student{"
            + "first_name='" + getFirstName() + '\''
            + ", last_name='" + getLastName() + '\''
            + ", slackNickname='" + getSlackNickname() + '\''
            + ", gitHubNickname='" + getGitHubNickname() + '\''
            + ", repository='" + repository + '\''
            + ", marks=" + Arrays.toString(marks)
            + '}';
    }
}
