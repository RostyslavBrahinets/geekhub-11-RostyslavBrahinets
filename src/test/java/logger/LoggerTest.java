package logger;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoggerTest {

    @Test
    void info_doNothing_forClassNameAndMessageAreNull() {
        assertDoesNotThrow(() -> Logger.info(null, null));
    }

    @Test
    void info_doNothing_forClassNameIsNull() {
        assertDoesNotThrow(() -> Logger.info(null, "Message"));
    }

    @Test
    void info_doNothing_forMessageIsNull() {
        assertDoesNotThrow(() -> Logger.info(getClass().getName(), null));
    }

    @Test
    void info_doNothing_forClassNameAndMessageAreEmpty() {
        assertDoesNotThrow(() -> Logger.info("", ""));
    }

    @Test
    void info_doNothing_forClassNameIsEmpty() {
        assertDoesNotThrow(() -> Logger.info("", "Message"));
    }

    @Test
    void info_doNothing_forMessageIsEmpty() {
        assertDoesNotThrow(() -> Logger.info(getClass().getName(), ""));
    }

    @Test
    void info_doNothing_withoutError() {
        assertDoesNotThrow(() -> Logger.info(getClass().getName(), "Message"));
    }

    @Test
    void warning_doNothing_forClassNameAndMessageAreNull() {
        assertDoesNotThrow(() -> Logger.warning(null, null));
    }

    @Test
    void warning_doNothing_forClassNameIsNull() {
        assertDoesNotThrow(() -> Logger.warning(null, "Message"));
    }

    @Test
    void warning_doNothing_forMessageIsNull() {
        assertDoesNotThrow(() -> Logger.warning(getClass().getName(), null));
    }

    @Test
    void warning_doNothing_forClassNameAndMessageAreEmpty() {
        assertDoesNotThrow(() -> Logger.warning("", ""));
    }

    @Test
    void warning_doNothing_forClassNameIsEmpty() {
        assertDoesNotThrow(() -> Logger.warning("", "Message"));
    }

    @Test
    void warning_doNothing_forMessageIsEmpty() {
        assertDoesNotThrow(() -> Logger.warning(getClass().getName(), ""));
    }

    @Test
    void warning_doNothing_withoutError() {
        assertDoesNotThrow(() -> Logger.warning(getClass().getName(), "Message"));
    }

    @Test
    void errorWith2Parameters_doNothing_forClassNameAndMessageAreNull() {
        assertDoesNotThrow(() -> Logger.error(null, null));
    }

    @Test
    void errorWith2Parameters_doNothing_forClassNameIsNull() {
        assertDoesNotThrow(() -> Logger.error(null, "Message"));
    }

    @Test
    void errorWith2Parameters_doNothing_forMessageIsNull() {
        assertDoesNotThrow(() -> Logger.error(getClass().getName(), null));
    }

    @Test
    void errorWith2Parameters_doNothing_forClassNameAndMessageAreEmpty() {
        assertDoesNotThrow(() -> Logger.error("", ""));
    }

    @Test
    void errorWith2Parameters_doNothing_forClassNameIsEmpty() {
        assertDoesNotThrow(() -> Logger.error("", "Message"));
    }

    @Test
    void errorWith2Parameters_doNothing_forMessageIsEmpty() {
        assertDoesNotThrow(() -> Logger.error(getClass().getName(), ""));
    }

    @Test
    void errorWith2Parameters_doNothing_withoutError() {
        assertDoesNotThrow(() -> Logger.error(getClass().getName(), "Message"));
    }
}