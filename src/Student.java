import java.util.Arrays;
import java.util.Objects;

public class Student {
    private String firstName;
    private String lastName;
    private String slackNickname;
    private String gitHubNickname;
    private String repository;
    private int[] marks;

    public Student(String firstName, String lastName, String slackNickname, String gitHubNickname, String repository,
                   int[] marks) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.slackNickname = slackNickname;
        this.gitHubNickname = gitHubNickname;
        this.repository = repository;
        this.marks = marks;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSlackNickname() {
        return slackNickname;
    }

    public void setSlackNickname(String slackNickname) {
        this.slackNickname = slackNickname;
    }

    public String getGitHubNickname() {
        return gitHubNickname;
    }

    public void setGitHubNickname(String gitHubNickname) {
        this.gitHubNickname = gitHubNickname;
    }

    public int[] getMarks() {
        return marks;
    }

    public void setMarks(int[] marks) {
        this.marks = marks;
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
        return Objects.equals(firstName, student.firstName)
            && Objects.equals(lastName, student.lastName)
            && Objects.equals(slackNickname, student.slackNickname)
            && Objects.equals(gitHubNickname, student.gitHubNickname)
            && Objects.equals(repository, student.repository)
            && Arrays.equals(marks, student.marks);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(firstName, lastName, slackNickname, gitHubNickname, repository);
        result = 31 * result + Arrays.hashCode(marks);
        return result;
    }

    @Override
    public String toString() {
        return "Student{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", slackNickname='" + slackNickname + '\'' +
            ", gitHubNickname='" + gitHubNickname + '\'' +
            ", repository='" + repository + '\'' +
            ", marks=" + Arrays.toString(marks) +
            '}';
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }
}
