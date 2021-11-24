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
}
