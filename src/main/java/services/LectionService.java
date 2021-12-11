package services;

import exceptions.LessonNotFoundException;
import exceptions.ValidationException;
import logger.Logger;
import models.Lection;
import sources.LectionSource;

public class LectionService {
    private final LectionSource lectionSource = LectionSource.getInstance();

    public void showLections() {
        for (Lection lection : lectionSource.getLections()) {
            if (lection == null) {
                return;
            }
            System.out.println(lection.getName());
        }
    }

    public void addLection(Lection lection) {
        try {
            lectionSource.addLection(lection);
        } catch (ValidationException e) {
            Logger.error(getClass().getName(), "Invalid name of lection", e);
        }
    }

    public void deleteLection(int id) {
        try {
            lectionSource.deleteLection(id);
        } catch (LessonNotFoundException e) {
            Logger.error(getClass().getName(), "Lection not found", e);
        }
    }

    public void showLection(int id) {
        try {
            System.out.println(lectionSource.getLectionById(id).getName());
        } catch (LessonNotFoundException e) {
            Logger.error(getClass().getName(), "Lection not found", e);
        }
    }
}
