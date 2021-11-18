import java.util.Objects;

public class Lecturer {
    private String firstName;
    private String lastName;
    private String slackNickname;
    private String gitHubNickname;

    public Lecturer(String firstName, String lastName, String slackNickname, String gitHubNickname) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.slackNickname = slackNickname;
        this.gitHubNickname = gitHubNickname;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Lecturer lecturer = (Lecturer) o;
        return Objects.equals(firstName, lecturer.firstName)
            && Objects.equals(lastName, lecturer.lastName)
            && Objects.equals(slackNickname, lecturer.slackNickname)
            && Objects.equals(gitHubNickname, lecturer.gitHubNickname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, slackNickname, gitHubNickname);
    }

    @Override
    public String toString() {
        return "Lecturer{" +
            "first_name='" + firstName + '\'' +
            ", last_name='" + lastName + '\'' +
            ", slackNickname='" + slackNickname + '\'' +
            ", gitHubNickname='" + gitHubNickname + '\'' +
            '}';
    }
}
