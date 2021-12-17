package services;

import models.Person;
import models.Role;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonServiceTest {
    private static PersonService personService;
    private static String firstName;
    private static String lastName;
    private static List<String> contacts;
    private static String nickname;
    private static String role;

    @BeforeAll
    static void setUp() {
        personService = new PersonService();
        firstName = "First name";
        lastName = "Last name";
        contacts = new ArrayList<>();
        contacts.add("Contact");
        nickname = "Nickname";
        role = "STUDENT";

        personService.addPerson(firstName, lastName, contacts, nickname, role);
    }

    @Test
    void getPeople_DoNothing_WithoutError() {
        List<Person> people = new ArrayList<>();
        people.add(new Person(firstName, lastName, contacts, nickname, Role.valueOf(role)));

        assertEquals(people, personService.getPeople());
    }

    @Test
    void addPerson_DoNothing_WithoutError() {
        assertDoesNotThrow(() -> personService.addPerson(firstName, lastName, contacts, nickname, role));
    }

    @Test
    void addPerson_LoggingException_ForFirstNameIsNull() {
        assertDoesNotThrow(() -> personService.addPerson(null, lastName, contacts, nickname, role));
    }

    @Test
    void addPerson_LoggingException_ForLastNameIsNull() {
        assertDoesNotThrow(() -> personService.addPerson(firstName, null, contacts, nickname, role));
    }

    @Test
    void addPerson_LoggingException_ForContactsIsNull() {
        assertDoesNotThrow(() -> personService.addPerson(firstName, lastName, null, nickname, role));
    }

    @Test
    void addPerson_LoggingException_ForGitHubNicknameIsNull() {
        assertDoesNotThrow(() -> personService.addPerson(firstName, lastName, contacts, null, role));
    }

    @Test
    void addPerson_LoggingException_ForRoleIsNull() {
        assertDoesNotThrow(() -> personService.addPerson(firstName, lastName, contacts, nickname, null));
    }

    @Test
    void addPerson_LoggingException_ForFirstNameIsEmpty() {
        assertDoesNotThrow(() -> personService.addPerson("", lastName, contacts, nickname, role));
    }

    @Test
    void addPerson_LoggingException_ForLastNameIsEmpty() {
        assertDoesNotThrow(() -> personService.addPerson(firstName, "", contacts, nickname, role));
    }

    @Test
    void addPerson_LoggingException_ForGitHubNicknameIsEmpty() {
        assertDoesNotThrow(() -> personService.addPerson(firstName, lastName, contacts, "", role));
    }

    @Test
    void addPerson_LoggingException_ForRoleIsEmpty() {
        assertDoesNotThrow(() -> personService.addPerson(firstName, lastName, contacts, nickname, ""));
    }

    @Test
    void deletePerson_DoNothing_WithoutError() {
        assertDoesNotThrow(() -> personService.deletePerson(0));
    }

    @Test
    void deletePerson_LoggingException_ForIdIsLessThenExpected() {
        assertDoesNotThrow(() -> personService.deletePerson(-1));
    }

    @Test
    void deletePerson_LoggingException_ForIdIsMoreThenExpected() {
        assertDoesNotThrow(() -> personService.deletePerson(personService.getPeople().size()));
    }

    @Test
    void getPerson_DoNothing_WithoutError() {
        Person person = new Person(firstName, lastName, contacts, nickname, Role.valueOf(role));

        assertEquals(person, personService.getPerson(0));
    }

    @Test
    void getPerson_ReturnNull_ForIdIsLessThenExpected() {
        assertNull(personService.getPerson(-1));
    }

    @Test
    void getPerson_ReturnNull_ForIdIsMoreThenExpected() {
        assertNull(personService.getPerson(personService.getPeople().size()));
    }
}