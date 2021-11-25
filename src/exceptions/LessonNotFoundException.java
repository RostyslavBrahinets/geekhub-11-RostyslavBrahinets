package exceptions;

public class LessonNotFoundException extends RuntimeException {
    public LessonNotFoundException() {
    }

    public LessonNotFoundException(String message) {
        super(message);
    }

    public LessonNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
