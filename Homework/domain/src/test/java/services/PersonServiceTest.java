package services;

import exceptions.InvalidArgumentException;
import exceptions.NotFoundException;
import exceptions.ValidationException;
import models.Person;
import models.Role;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PersonServiceTest {
    private static PersonService personService;
    private static String firstName;
    private static String lastName;
    private static List<String> contacts;
    private static String nickname;
    private static String role;

    @BeforeAll
    static void setDataInRepository() {
        PersonRepository personRepository = PersonRepository.getInstance();
        firstName = "First name";
        lastName = "Last name";
        contacts = new ArrayList<>();
        contacts.add("Contact");
        nickname = "Nickname";
        role = "STUDENT";
        personRepository.addPerson(
            new Person(firstName, lastName, contacts, nickname, Role.valueOf(role))
        );
    }

    @BeforeEach
    void setUp() {
        personService = new PersonService();
    }

    @Test
    void getPeople_DoNothing_WithoutError() {
        Optional<List<Person>> people = Optional.of(new ArrayList<>());
        people.get().add(
            new Person(firstName, lastName, contacts, nickname, Role.valueOf(role))
        );

        assertEquals(people, personService.getPeople());
    }

    @Test
    void addPerson_DoNothing_WithoutError() {
        assertDoesNotThrow(
            () -> personService.addPerson(firstName, lastName, contacts, nickname, role)
        );
    }

    @Test
    void addPerson_ThrowValidationException_ForFirstNameIsNull() {
        assertThrows(
            ValidationException.class,
            () -> personService.addPerson(null, lastName, contacts, nickname, role)
        );
    }

    @Test
    void addPerson_ThrowValidationException_ForLastNameIsNull() {
        assertThrows(
            ValidationException.class,
            () -> personService.addPerson(firstName, null, contacts, nickname, role)
        );
    }

    @Test
    void addPerson_ThrowValidationException_ForContactsIsNull() {
        assertThrows(
            ValidationException.class,
            () -> personService.addPerson(firstName, lastName, null, nickname, role)
        );
    }

    @Test
    void addPerson_ThrowValidationException_ForGitHubNicknameIsNull() {
        assertThrows(
            ValidationException.class,
            () -> personService.addPerson(firstName, lastName, contacts, null, role)
        );
    }

    @Test
    void addPerson_ThrowInvalidArgumentException_ForRoleIsNull() {
        assertThrows(
            InvalidArgumentException.class,
            () -> personService.addPerson(firstName, lastName, contacts, nickname, null)
        );
    }

    @Test
    void addPerson_ThrowValidationException_ForFirstNameIsEmpty() {
        assertThrows(
            ValidationException.class,
            () -> personService.addPerson("", lastName, contacts, nickname, role)
        );
    }

    @Test
    void addPerson_ThrowValidationException_ForLastNameIsEmpty() {
        assertThrows(
            ValidationException.class,
            () -> personService.addPerson(firstName, "", contacts, nickname, role)
        );
    }

    @Test
    void addPerson_ThrowValidationException_ForGitHubNicknameIsEmpty() {
        assertThrows(
            ValidationException.class,
            () -> personService.addPerson(firstName, lastName, contacts, "", role)
        );
    }

    @Test
    void addPerson_ThrowInvalidArgumentException_ForRoleIsEmpty() {
        assertThrows(
            InvalidArgumentException.class,
            () -> personService.addPerson(firstName, lastName, contacts, nickname, "")
        );
    }

    @Test
    void addPerson_ThrowInvalidArgumentException_ForRoleIsInvalid() {
        assertThrows(
            InvalidArgumentException.class,
            () -> personService.addPerson(firstName, lastName, contacts, nickname, "ROLE")
        );
    }

    @Test
    void deletePerson_DoNothing_WithoutError() {
        assertDoesNotThrow(
            () -> personService.deletePerson(0)
        );
    }

    @Test
    void deletePerson_ThrowNotFoundException_ForIdIsLessThenExpected() {
        assertThrows(
            NotFoundException.class,
            () -> personService.deletePerson(-1)
        );
    }

    @Test
    void deletePerson_ThrowNotFoundException_ForIdIsMoreThenExpected() {
        Optional<List<Person>> people = personService.getPeople();
        int peopleSize = Integer.MAX_VALUE;
        if (people.isPresent()) {
            peopleSize = people.get().size();
        }
        int size = peopleSize;

        assertThrows(
            NotFoundException.class,
            () -> personService.deletePerson(size)
        );
    }

    @Test
    void getPerson_DoNothing_WithoutError() {
        Person expectedPerson = new Person(firstName, lastName, contacts, nickname, Role.valueOf(role));
        Optional<Person> person = personService.getPerson(0);

        Person actualPerson = null;
        if (person.isPresent()) {
            actualPerson = person.get();
        }

        assertEquals(expectedPerson, actualPerson);
    }

    @Test
    void getPerson_ThrowNotFoundException_ForIdIsLessThenExpected() {
        assertThrows(
            NotFoundException.class,
            () -> personService.getPerson(-1)
        );
    }

    @Test
    void getPerson_ThrowNotFoundException_ForIdIsMoreThenExpected() {
        Optional<List<Person>> people = personService.getPeople();
        int peopleSize = Integer.MAX_VALUE;
        if (people.isPresent()) {
            peopleSize = people.get().size();
        }
        int size = peopleSize;

        assertThrows(
            NotFoundException.class,
            () -> personService.getPerson(size)
        );
    }
}