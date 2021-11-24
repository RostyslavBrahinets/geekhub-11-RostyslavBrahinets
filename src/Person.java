public class Person {
    private String firstName;
    private String lastName;
    private String slackNickname;
    private String gitHubNickname;

    protected Person(String firstName, String lastName, String slackNickname,
                     String gitHubNickname) {
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
}
