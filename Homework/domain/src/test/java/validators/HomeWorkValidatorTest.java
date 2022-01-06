package validators;

import exceptions.NotFoundException;
import exceptions.ValidationException;
import models.HomeWork;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.HomeWorkRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HomeWorkValidatorTest {
    private HomeWorkValidator validator;
    private static LocalDateTime deadline;

    @BeforeAll
    static void setDataInRepository() {
        HomeWorkRepository homeworkSource = HomeWorkRepository.getInstance();
        deadline = LocalDateTime.now();
        homeworkSource.addHomeWork(
            new HomeWork("Task", deadline)
        );
    }

    @BeforeEach
    void setUp() {
        validator = new HomeWorkValidator();
    }

    @Test
    void validate_DoNothing_WithoutError() {
        assertDoesNotThrow(
            () -> validator.validate("Task", deadline)
        );
    }

    @Test
    void validate_ThrowsValidationException_ForTaskIsNull() {
        assertThrows(
            ValidationException.class,
            () -> validator.validate(null, deadline)
        );
    }

    @Test
    void validate_ThrowsValidationException_ForDeadlineIsNull() {
        assertThrows(
            ValidationException.class,
            () -> validator.validate("Task", null)
        );
    }

    @Test
    void validate_ThrowsValidationException_ForTaskIsEmpty() {
        assertThrows(
            ValidationException.class,
            () -> validator.validate("", deadline)
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
        HomeWorkRepository homeWorkRepository = HomeWorkRepository.getInstance();
        List<HomeWork> homeWorks = homeWorkRepository.getHomeWorks();
        int size = homeWorks.size();

        assertThrows(
            NotFoundException.class,
            () -> validator.validate(size)
        );
    }
}