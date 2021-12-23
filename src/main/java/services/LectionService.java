package services;

import exceptions.NotFoundException;
import exceptions.ValidationException;
import logger.Logger;
import models.HomeWork;
import models.Lection;
import models.Person;
import models.Resource;
import sources.LectionSource;
import validators.LectionValidator;

import java.time.LocalDate;
import java.util.*;

public class LectionService {
    private final LectionSource lectionSource = LectionSource.getInstance();
    private final LectionValidator validator = new LectionValidator();

    public Optional<List<Lection>> getLections() {
        return Optional.ofNullable(lectionSource.getLections());
    }

    public void addLection(String name, String describe, List<Resource> resources, Person lecturer,
                           List<HomeWork> homeWorks, LocalDate creationDate) {
        try {
            validator.validate(name, describe, resources, lecturer, homeWorks);
            lectionSource.addLection(new Lection(name, describe, resources, lecturer, homeWorks, creationDate));
        } catch (ValidationException e) {
            Logger.error(getClass().getName(), e.getMessage(), e);
        }
    }

    public void deleteLection(int id) {
        try {
            validator.validate(id);
            lectionSource.deleteLection(id);
        } catch (NotFoundException e) {
            Logger.error(getClass().getName(), e.getMessage(), e);
        }
    }

    public Optional<Lection> getLection(int id) {
        Lection lection = null;

        try {
            validator.validate(id);
            lection = lectionSource.getLection(id);
        } catch (NotFoundException e) {
            Logger.error(getClass().getName(), e.getMessage(), e);
        }

        return Optional.ofNullable(lection);
    }

    public Map<Lection, List<Resource>> getResourcesGroupedByLecture() {
        Map<Lection, List<Resource>> groupedResources = new HashMap<>();

        for (Lection lection : lectionSource.getLections()) {
            groupedResources.put(lection, lection.getResources());
        }

        return groupedResources;
    }

    public Map<Lection, List<HomeWork>> getHomeWorksGroupedByLecture() {
        Map<Lection, List<HomeWork>> groupedHomeWorks = new HashMap<>();

        for (Lection lection : lectionSource.getLections()) {
            groupedHomeWorks.put(lection, lection.getHomeWorks());
        }

        return groupedHomeWorks;
    }

    public void sortLectionByDateASC() {
        Collections.sort(lectionSource.getLections());
    }

    public void sortLectionByDateDESC() {
        lectionSource.getLections().sort(Collections.reverseOrder());
    }
}
