import exceptions.LessonNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import work.Lection;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    void showLectionByNumber_doNothing_forLectionsAndNumberOfLectionAreNull() {
        assertDoesNotThrow(() ->  useCommand.showLectionByNumber(null, null));
    }

    @Test
    void showLectionByNumber_doNothing_forLectionsIsNull() {
        assertDoesNotThrow(() ->  useCommand.showLectionByNumber(null, "0"));
    }

    @Test
    void showLectionByNumber_doNothing_forNumberOfLectionIsNull() {
        assertDoesNotThrow(() ->  useCommand.showLectionByNumber(lections, null));
    }

    @Test
    void showLectionByNumber_error_forNumberOfLectionIsEmpty() {
        assertThrows(
            LessonNotFoundException.class,
            () ->  useCommand.showLectionByNumber(lections, "")
        );
    }

    @Test
    void showLectionByNumber_error_forNumberOfLectionIsLessThenRequired() {
        assertThrows(
            LessonNotFoundException.class,
            () ->  useCommand.showLectionByNumber(lections, "-1")
        );
    }

    @Test
    void showLectionByNumber_error_forNumberOfLectionIsMoreThenRequired() {
        assertThrows(
            LessonNotFoundException.class,
            () ->  useCommand.showLectionByNumber(lections, "99")
        );
    }

    @Test
    void showLectionByNumber_error_forNumberOfLectionIsInvalid() {
        assertThrows(
            LessonNotFoundException.class,
            () ->  useCommand.showLectionByNumber(lections, "a")
        );
    }

    @Test
    void showLectionByNumber_doNothing_withoutError() {
        assertDoesNotThrow(() ->  useCommand.showLectionByNumber(lections, "0"));
    }
}