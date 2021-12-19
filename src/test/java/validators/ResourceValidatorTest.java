package validators;

import exceptions.InvalidArgumentException;
import exceptions.NotFoundException;
import exceptions.ValidationException;
import models.Resource;
import models.ResourceType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sources.ResourcesSource;

import static org.junit.jupiter.api.Assertions.*;

class ResourceValidatorTest {
    private ResourceValidator validator;

    @BeforeAll
    static void setDataInSource() {
        ResourcesSource resourcesSource = ResourcesSource.getInstance();
        resourcesSource.addResource(new Resource("Resource", ResourceType.URL, "Data"));
    }

    @BeforeEach
    void setUp() {
        validator = new ResourceValidator();
    }

    @Test
    void validate_DoNothing_WithoutError() {
        assertDoesNotThrow(() -> validator.validate("Resource", "URL", "Data"));
    }

    @Test
    void validate_ThrowsValidationException_ForNameIsNull() {
        assertThrows(ValidationException.class, () -> validator.validate(null, "URL", "Data"));
    }

    @Test
    void validate_ThrowsValidationException_ForTypeIsNull() {
        assertThrows(InvalidArgumentException.class, () -> validator.validate("Resource", null, "Data"));
    }

    @Test
    void validate_ThrowsValidationException_ForDataIsNull() {
        assertThrows(ValidationException.class, () -> validator.validate("Resource", "URL", null));
    }

    @Test
    void validate_ThrowsValidationException_ForNameIsEmpty() {
        assertThrows(ValidationException.class, () -> validator.validate("", "URL", "Data"));
    }

    @Test
    void validate_ThrowsValidationException_ForTypeIsEmpty() {
        assertThrows(InvalidArgumentException.class, () -> validator.validate("Resource", "", "Data"));
    }

    @Test
    void validate_ThrowsValidationException_ForDataIsEmpty() {
        assertThrows(ValidationException.class, () -> validator.validate("Resource", "URL", ""));
    }

    @Test
    void validate_ThrowsValidationException_ForTypeIsNotInListOfTypes() {
        assertThrows(InvalidArgumentException.class, () -> validator.validate("Resource", "TYPE", "Data"));
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