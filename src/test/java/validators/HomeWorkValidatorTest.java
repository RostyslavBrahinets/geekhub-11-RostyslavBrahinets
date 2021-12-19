package validators;

import exceptions.NotFoundException;
import exceptions.ValidationException;
import models.HomeWork;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sources.HomeWorkSource;

import static org.junit.jupiter.api.Assertions.*;

class HomeWorkValidatorTest {
    private HomeWorkValidator validator;

    @BeforeAll
    static void setDataInSource() {
        HomeWorkSource homeworkSource = HomeWorkSource.getInstance();
        homeworkSource.addHomeWork(new HomeWork("Task"));
    }

    @BeforeEach
    void setUp() {
        validator = new HomeWorkValidator();
    }

    @Test
    void validate_DoNothing_WithoutError() {
        assertDoesNotThrow(() -> validator.validate("Task"));
    }

    @Test
    void validate_ThrowsValidationException_ForTaskIsNull() {
        assertThrows(ValidationException.class, () -> validator.validate(null));
    }

    @Test
    void validate_ThrowsValidationException_ForTaskIsEmpty() {
        assertThrows(ValidationException.class, () -> validator.validate(""));
    }

    @Test
    void validateId_DoNothing_WithoutError() {
        assertDoesNotThrow(() -> validator.validate(0));
    }

    @Test
    void validateId_ThrowsNotFoundException_ForIdIsLessThenExpected() {
        assertThrows(NotFoundException.class, () -> validator.validate(-1));
    }

    @Test
    void validateId_ThrowsNotFoundException_ForIdIsMoreThenExpected() {
        assertThrows(NotFoundException.class, () -> validator.validate(1));
    }
}