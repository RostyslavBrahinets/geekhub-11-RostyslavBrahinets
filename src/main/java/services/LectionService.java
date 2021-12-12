package services;

import exceptions.LessonNotFoundException;
import exceptions.ValidationException;
import logger.Logger;
import models.Lection;
import sources.LectionSource;

public class LectionService {
    private final LectionSource lectionSource = LectionSource.getInstance();

    public Lection[] getLections() {
        return lectionSource.getLections();
    }

    public void addLection(Lection lection) {
        try {
            lectionSource.addLection(lection);
        } catch (ValidationException e) {
            Logger.error(getClass().getName(), "Lection is invalid", e);
        }
    }

    public void deleteLection(int id) {
        try {
            lectionSource.deleteLection(id);
        } catch (LessonNotFoundException e) {
            Logger.error(getClass().getName(), "Lection not found", e);
        }
    }

    public Lection getLection(int id) {
        Lection lection = null;
        try {
            lection = lectionSource.getLectionById(id);
        } catch (LessonNotFoundException e) {
            Logger.error(getClass().getName(), "Lection not found", e);
        }

        return lection;
    }
}
