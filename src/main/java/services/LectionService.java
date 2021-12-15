package services;

import exceptions.LessonNotFoundException;
import exceptions.ValidationException;
import logger.Logger;
import models.Lection;
import models.Resource;
import sources.LectionSource;
import validators.LectionValidator;

import java.util.List;

public class LectionService {
    private final LectionSource lectionSource = LectionSource.getInstance();
    private final LectionValidator validator = new LectionValidator();

    public List<Lection> getLections() {
        return lectionSource.getLections();
    }

    public void addLection(String name, List<Resource> resources) {
        try {
            validator.validate(name, resources);
            lectionSource.addLection(new Lection(name, resources));
        } catch (ValidationException e) {
            Logger.error(getClass().getName(), e.getMessage(), e);
        }
    }

    public void deleteLection(int id) {
        try {
            validator.validate(id);
            lectionSource.deleteLection(id);
        } catch (LessonNotFoundException e) {
            Logger.error(getClass().getName(), e.getMessage(), e);
        }
    }

    public Lection getLection(int id) {
        Lection lection = null;

        try {
            validator.validate(id);
            lection = lectionSource.getLectionById(id);
        } catch (LessonNotFoundException e) {
            Logger.error(getClass().getName(), e.getMessage(), e);
        }

        return lection;
    }
}
