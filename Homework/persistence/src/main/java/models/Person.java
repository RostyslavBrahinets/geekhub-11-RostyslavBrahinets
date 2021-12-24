package models;

import java.util.List;
import java.util.Objects;

public class Person {
    private final String firstName;
    private final String lastName;
    private final List<String> contacts;
    private final String gitHubNickname;
    private final Role role;

    public Person(String firstName, String lastName, List<String> contacts,
                  String gitHubNickname, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contacts = contacts;
        this.gitHubNickname = gitHubNickname;
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
        return gitHubNickname;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(contacts, person.contacts) && Objects.equals(gitHubNickname, person.gitHubNickname) && role == person.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, contacts, gitHubNickname, role);
    }

    @Override
    public String toString() {
        return "Person {"
            + "firstName: '" + firstName + '\''
            + ", lastName: '" + lastName + '\''
            + ", contacts: " + contacts
            + ", gitHubNickname: '" + gitHubNickname + '\''
            + ", role: " + role
            + '}';
    }
}
