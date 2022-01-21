package models;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public record Person(
    String firstName,
    String lastName,
    List<String> contacts,
    String gitHubNickname,
    Role role
) implements Serializable {
    private static final int serialVersionUID = 4;

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
