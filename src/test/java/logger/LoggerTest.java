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
}