package validators;

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

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PersonValidatorTest {

    private PersonValidator validator;
    private static List<String> contacts;

    @BeforeAll
    static void setDataInRepository() {
        PersonRepository personSource = PersonRepository.getInstance();
        contacts = new ArrayList<>();
        contacts.add("Contact");
        personSource.addPerson(
            new Person(
                "First name",
                "Last name",
                contacts,
                "Nickname",
                Role.STUDENT
            )
        );
    }

    @BeforeEach
    void setUp() {
        validator = new PersonValidator();
    }

    @Test
    void validate_DoNothing_WithoutError() {
        assertDoesNotThrow(() -> validator.validate(
            "First name",
            "Last name",
            contacts,
            "Nickname",
            "STUDENT"
        ));
    }

    @Test
    void validate_ThrowsValidationException_ForFirstNameIsNull() {
        assertThrows(
            ValidationException.class,
            () -> validator.validate(
                null,
                "Last name",
                contacts,
                "Nickname",
                "STUDENT"
            ));
    }

    @Test
    void validate_ThrowsValidationException_ForLastNameIsNull() {
        assertThrows(
            ValidationException.class,
            () -> validator.validate(
                "First name",
                null,
                contacts,
                "Nickname",
                "STUDENT"
            ));
    }

    @Test
    void validate_ThrowsValidationException_ForContactsIsNull() {
        assertThrows(
            ValidationException.class,
            () -> validator.validate(
                "First name",
                "Last name",
                null,
                "Nickname",
                "STUDENT"
            ));
    }

    @Test
    void validate_ThrowsValidationException_ForNicknameIsNull() {
        assertThrows(
            ValidationException.class,
            () -> validator.validate(
                "First name",
                "Last name",
                contacts,
                null,
                "STUDENT"
            ));
    }

    @Test
    void validate_ThrowsInvalidArgumentException_ForRoleIsNull() {
        assertThrows(
            InvalidArgumentException.class,
            () -> validator.validate(
                "First name",
                "Last name",
                contacts,
                "Nickname",
                null
            ));
    }

    @Test
    void validate_ThrowsValidationException_ForFirstNameIsEmpty() {
        assertThrows(
            ValidationException.class,
            () -> validator.validate(
                "",
                "Last name",
                contacts,
                "Nickname",
                "STUDENT"
            ));
    }

    @Test
    void validate_ThrowsValidationException_ForLastNameIsEmpty() {
        assertThrows(
            ValidationException.class,
            () -> validator.validate(
                "First name",
                "",
                contacts,
                "Nickname",
                "STUDENT"
            ));
    }

    @Test
    void validate_ThrowsValidationException_ForNicknameIsEmpty() {
        assertThrows(
            ValidationException.class,
            () -> validator.validate(
                "First name",
                "Last name",
                contacts,
                "",
                "STUDENT"
            ));
    }

    @Test
    void validate_ThrowsInvalidArgumentException_ForRoleIsEmpty() {
        assertThrows(
            InvalidArgumentException.class,
            () -> validator.validate(
                "First name",
                "Last name",
                contacts,
                "Nickname",
                ""
            ));
    }

    @Test
    void validate_ThrowsInvalidArgumentException_ForRoleIsNotInListOfRoles() {
        assertThrows(
            InvalidArgumentException.class,
            () -> validator.validate(
                "First name",
                "Last name",
                contacts,
                "Nickname",
                "ROLE"
            ));
    }

    @Test
    void validateId_DoNothing_WithoutError() {
        assertDoesNotThrow(
            () -> validator.validate(0)
        );
    }

    @Test
    void validateId_ThrowsNotFoundException_ForIdIsLessThenExpected() {
        assertThrows(
            NotFoundException.class,
            () -> validator.validate(-1)
        );
    }

    @Test
    void validateId_ThrowsNotFoundException_ForIdIsMoreThenExpected() {
        assertThrows(
            NotFoundException.class,
            () -> validator.validate(1)
        );
    }
}