package exceptions;

public class LessonNotFoundException extends ArrayIndexOutOfBoundsException {
    public LessonNotFoundException() {
    }

    public LessonNotFoundException(String message) {
        super(message);
    }

}
