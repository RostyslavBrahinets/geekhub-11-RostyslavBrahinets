import java.util.Objects;

public class Lecturer extends Person {
    public Lecturer(String firstName, String lastName, String slackNickname, String gitHubNickname) {
        super(firstName, lastName, slackNickname, gitHubNickname);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Lecturer lecturer = (Lecturer) o;
        return Objects.equals(getFirstName(), lecturer.getFirstName())
            && Objects.equals(getLastName(), lecturer.getLastName())
            && Objects.equals(getSlackNickname(), lecturer.getSlackNickname())
            && Objects.equals(getGitHubNickname(), lecturer.getGitHubNickname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getSlackNickname(), getGitHubNickname());
    }

    @Override
    public String toString() {
        return "Lecturer{" +
            "first_name='" + getFirstName() + '\'' +
            ", last_name='" + getLastName() + '\'' +
            ", slackNickname='" + getSlackNickname() + '\'' +
            ", gitHubNickname='" + getGitHubNickname() + '\'' +
            '}';
    }
}
