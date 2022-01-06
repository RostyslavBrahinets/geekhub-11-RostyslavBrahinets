package validators;

import exceptions.NotFoundException;
import exceptions.ValidationException;
import models.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.LectionRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LectionValidatorTest {
    private LectionValidator validator;
    private static String name;
    private static String describe;
    private static List<Resource> resources;
    private static Person lecturer;
    private static List<HomeWork> homeWorks;

    @BeforeAll
    static void setDataInRepository() {
        resources = new ArrayList<>();
        resources.add(
            new Resource("Resource", ResourceType.URL, "Data")
        );
        List<String> contacts = new ArrayList<>();
        contacts.add("Contact");
        lecturer = new Person(
            "First name",
            "Last name",
            contacts,
            "Nickname",
            Role.TEACHER);
        homeWorks = new ArrayList<>();
        homeWorks.add(
            new HomeWork("Task", LocalDateTime.now())
        );
        name = "Lection";
        describe = "Describe";

        LectionRepository lectionRepository = LectionRepository.getInstance();
        lectionRepository.addLection(
            new Lection(
                name,
                describe,
                resources,
                lecturer,
                homeWorks,
                LocalDate.now())
        );
    }

    @BeforeEach
    void setUp() {
        validator = new LectionValidator();
    }

    @Test
    void validate_DoNothing_WithoutError() {
        assertDoesNotThrow(
            () -> validator.validate(name, describe, resources, lecturer, homeWorks)
        );
    }

    @Test
    void validate_ThrowsValidationException_ForNameIsNull() {
        assertThrows(
            ValidationException.class,
            () -> validator.validate(null, describe, resources, lecturer, homeWorks)
        );
    }

    @Test
    void validate_ThrowsValidationException_ForDescribeIsNull() {
        assertThrows(
            ValidationException.class,
            () -> validator.validate(name, null, resources, lecturer, homeWorks)
        );
    }

    @Test
    void validate_ThrowsValidationException_ForResourcesIsNull() {
        assertThrows(
            ValidationException.class,
            () -> validator.validate(name, describe, null, lecturer, homeWorks)
        );
    }

    @Test
    void validate_ThrowsValidationException_ForLecturerIsNull() {
        assertThrows(
            ValidationException.class,
            () -> validator.validate(name, describe, resources, null, homeWorks)
        );
    }

    @Test
    void validate_ThrowsValidationException_ForHomeWorksIsNull() {
        assertThrows(
            ValidationException.class,
            () -> validator.validate(name, describe, resources, lecturer, null)
        );
    }

    @Test
    void validate_ThrowsValidationException_ForNameIsEmpty() {
        assertThrows(
            ValidationException.class,
            () -> validator.validate("", describe, resources, lecturer, homeWorks)
        );
    }

    @Test
    void validate_ThrowsValidationException_ForDescribeIsEmpty() {
        assertThrows(
            ValidationException.class,
            () -> validator.validate(name, "", resources, lecturer, homeWorks)
        );
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