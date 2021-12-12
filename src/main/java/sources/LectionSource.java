package sources;

import exceptions.LessonNotFoundException;
import exceptions.ValidationException;
import models.Lection;

import java.util.Arrays;

public class LectionSource {
    private static LectionSource instance;
    private Lection[] lections;

    public LectionSource() {
        lections = new Lection[0];
    }

    public Lection[] getLections() {
        return lections;
    }

    public void addLection(Lection lection) {
        if (lection == null || lection.getName().isBlank()) {
            throw new ValidationException("Lection is invalid");
        }

        lections = Arrays.copyOf(lections, lections.length + 1);
        lections[lections.length - 1] = lection;
    }

    public void deleteLection(int id) {
        if (id < 0 || id >= lections.length) {
            throw new LessonNotFoundException("Lection not found");
        }

        Lection[] newLections = new Lection[lections.length - 1];

        System.arraycopy(lections, 0, newLections, 0, id);

        if (lections.length - (id + 1) >= 0) {
            System.arraycopy(lections, id + 1, newLections, id, lections.length - (id + 1));
        }

        lections = newLections;
    }

    public Lection getLectionById(int id) {
        if (id < 0 || id >= lections.length) {
            throw new LessonNotFoundException("Lection not found");
        }

        return lections[id];
    }

    public static LectionSource getInstance() {
        if (instance == null) {
            instance = new LectionSource();
        }

        return instance;
    }
}
