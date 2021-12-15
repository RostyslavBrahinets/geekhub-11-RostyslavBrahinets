package models;

import java.util.List;

public class Person {
    private final String firstName;
    private final String lastName;
    private final List<String> contacts;
    private final String email;
    private final Role role;

    protected Person(String firstName, String lastName, List<String> contacts,
                     String gitHubNickname, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contacts = contacts;
        this.email = gitHubNickname;
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<String> getContacts() {
        return contacts;
    }

    public String getGitHubNickname() {
        return email;
    }

    public Role getRole() {
        return role;
    }
}
