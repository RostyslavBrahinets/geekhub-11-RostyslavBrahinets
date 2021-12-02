import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import work.Lection;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

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
    void showAllLections_nothing_forNull() {
        assertDoesNotThrow(() -> useCommand.showAllLections(null));
    }

    @Test
    void showAllLections_withoutError() {
        assertDoesNotThrow(() -> useCommand.showAllLections(lections));
    }
}