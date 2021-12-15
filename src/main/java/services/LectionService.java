package services;

import exceptions.LessonNotFoundException;
import exceptions.ValidationException;
import logger.Logger;
import models.Lection;
import models.Resource;
import sources.LectionSource;

import java.util.List;

public class LectionService {
    private final LectionSource lectionSource = LectionSource.getInstance();

    public List<Lection> getLections() {
        return lectionSource.getLections();
    }

    public void addLection(String name, List<Resource> resources) {
        try {
            if (name == null || name.isBlank() || resources == null) {
                throw new ValidationException("Lection is invalid");
            }

            lectionSource.addLection(new Lection(name, resources));
        } catch (ValidationException e) {
            Logger.error(getClass().getName(), "Lection is invalid", e);
        }
    }

    public void deleteLection(int id) {
        try {
            if (id < 0 || id >= lectionSource.getLections().size()) {
                throw new LessonNotFoundException("Lection not found");
            }

            lectionSource.deleteLection(id);
        } catch (LessonNotFoundException e) {
            Logger.error(getClass().getName(), "Lection not found", e);
        }
    }

    public Lection getLection(int id) {
        Lection lection = null;

        try {
            if (id < 0 || id >= lectionSource.getLections().size()) {
                throw new LessonNotFoundException("Lection not found");
            }

            lection = lectionSource.getLectionById(id);
        } catch (LessonNotFoundException e) {
            Logger.error(getClass().getName(), "Lection not found", e);
        }

        return lection;
    }
}
