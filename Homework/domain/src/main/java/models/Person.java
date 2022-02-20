package models;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public final class Person implements Serializable {
    @Serial
    private static final long serialVersionUID = 4L;
    private int id;
    private String firstName;
    private String lastName;
    private List<String> contacts;
    private String gitHubNickname;
    private Role role;

    public Person() {
    }

    public Person(
        String firstName,
        String lastName,
        List<String> contacts,
        String gitHubNickname,
        Role role
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contacts = contacts;
        this.gitHubNickname = gitHubNickname;
        this.role = role;
    }

    public Person(
        int id,
        String firstName,
        String lastName,
        List<String> contacts,
        String gitHubNickname,
        Role role
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contacts = contacts;
        this.gitHubNickname = gitHubNickname;
        this.role = role;
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

    public int getId() {
        return id;
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

}
