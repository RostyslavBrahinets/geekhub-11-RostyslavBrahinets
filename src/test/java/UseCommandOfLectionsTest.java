import exceptions.LessonNotFoundException;
import exceptions.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import work.Lection;

import static org.junit.jupiter.api.Assertions.*;


class UseCommandOfLectionsTest {
    UseCommandOfLections useCommand;
    Lection[] lections;

    @BeforeEach
    void setUp() {
        useCommand = new UseCommandOfLections();
        lections = new Lection[]{
            new Lection("Intro"),
            new Lection("Basics"),
            new Lection("OOP")
        };
    }

    @Test
    void showAllLections_doNothing_forNull() {
        assertDoesNotThrow(() -> useCommand.showAllLections(null));
    }

    @Test
    void showAllLections_withoutError() {
        assertDoesNotThrow(() -> useCommand.showAllLections(lections));
    }

    @Test
    void addNewLection_error_forLectionsAndNameAreNull() {
        assertThrows(
            ValidationException.class,
            () -> lections = useCommand.addNewLection(null, null)
        );
    }

    @Test
    void addNewLection_doNothing_forLectionsIsNull() {
        assertDoesNotThrow(
            () -> lections = useCommand.addNewLection(null, "L04")
        );
    }

    @Test
    void addNewLection_error_forNameIsNull() {
        assertThrows(
            ValidationException.class,
            () -> lections = useCommand.addNewLection(lections, null)
        );
    }

    @Test
    void addNewLection_error_forNameIsEmpty() {
        assertThrows(
            ValidationException.class,
            () -> lections = useCommand.addNewLection(lections, "")
        );
    }

    @Test
    void addNewLection_error_forNameIsBlank() {
        assertThrows(
            ValidationException.class,
            () -> lections = useCommand.addNewLection(lections, " ")
        );
    }

    @Test
    void addNewLection_withoutError() {
        lections = useCommand.addNewLection(lections, "L04");
        assertEquals("L04", lections[lections.length - 1].getName());
    }

    @Test
    void deleteLectionByNumber_error_forLectionsAndNameAreNull() {
        assertThrows(
            LessonNotFoundException.class,
            () -> lections = useCommand.deleteLectionByNumber(null, null)
        );
    }

    @Test
    void deleteLectionByNumber_error_forLectionsIsNull() {
        assertThrows(
            LessonNotFoundException.class,
            () -> lections = useCommand.deleteLectionByNumber(null, "0")
        );
    }

    @Test
    void deleteLectionByNumber_error_forNameIsNull() {
        assertThrows(
            LessonNotFoundException.class,
            () -> lections = useCommand.deleteLectionByNumber(lections, null)
        );
    }

    @Test
    void deleteLectionByNumber_error_forNameIsEmpty() {
        assertThrows(
            LessonNotFoundException.class,
            () -> lections = useCommand.deleteLectionByNumber(lections, "")
        );
    }

    @Test
    void deleteLectionByNumber_error_forNameIsLessThanRequired() {
        assertThrows(
            LessonNotFoundException.class,
            () -> lections = useCommand.deleteLectionByNumber(lections, "-1")
        );
    }

    @Test
    void deleteLectionByNumber_error_forNameIsMoreThanRequired() {
        assertThrows(
            LessonNotFoundException.class,
            () -> lections = useCommand.deleteLectionByNumber(lections, "99")
        );
    }

    @Test
    void deleteLectionByNumber_error_forNameIsInvalid() {
        assertThrows(
            LessonNotFoundException.class,
            () -> lections = useCommand.deleteLectionByNumber(lections, "a")
        );
    }

    @Test
    void deleteLectionByNumber_withoutError() {
        Lection[] newLections = useCommand.deleteLectionByNumber(lections, "0");
        assertEquals(lections.length - 1, newLections.length);
    }

    @Test
    void showLectionByNumber_doNothing_forLectionsAndNumberOfLectionAreNull() {
        assertDoesNotThrow(() -> useCommand.showLectionByNumber(null, null));
    }

    @Test
    void showLectionByNumber_doNothing_forLectionsIsNull() {
        assertDoesNotThrow(() -> useCommand.showLectionByNumber(null, "0"));
    }

    @Test
    void showLectionByNumber_doNothing_forNumberOfLectionIsNull() {
        assertDoesNotThrow(() -> useCommand.showLectionByNumber(lections, null));
    }

    @Test
    void showLectionByNumber_error_forNumberOfLectionIsEmpty() {
        assertThrows(
            LessonNotFoundException.class,
            () -> useCommand.showLectionByNumber(lections, "")
        );
    }

    @Test
    void showLectionByNumber_error_forNumberOfLectionIsLessThenRequired() {
        assertThrows(
            LessonNotFoundException.class,
            () -> useCommand.showLectionByNumber(lections, "-1")
        );
    }

    @Test
    void showLectionByNumber_error_forNumberOfLectionIsMoreThenRequired() {
        assertThrows(
            LessonNotFoundException.class,
            () -> useCommand.showLectionByNumber(lections, "99")
        );
    }

    @Test
    void showLectionByNumber_error_forNumberOfLectionIsInvalid() {
        assertThrows(
            LessonNotFoundException.class,
            () -> useCommand.showLectionByNumber(lections, "a")
        );
    }

    @Test
    void showLectionByNumber_withoutError() {
        assertDoesNotThrow(() -> useCommand.showLectionByNumber(lections, "0"));
    }
}