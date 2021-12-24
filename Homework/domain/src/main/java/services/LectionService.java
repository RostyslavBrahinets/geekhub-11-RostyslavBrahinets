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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LectionService {
    private final LectionSource lectionSource = LectionSource.getInstance();
    private final LectionValidator validator = new LectionValidator();

    public List<Lection> getLections() {
        return lectionSource.getLections();
    }

    public void addLection(String name, String describe, List<Resource> resources, Person lecturer,
                           List<HomeWork> homeWorks) {
        try {
            validator.validate(name, describe, resources, lecturer, homeWorks);
            lectionSource.addLection(new Lection(name, describe, resources, lecturer, homeWorks, LocalDate.now()));
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

    public Lection getLection(int id) {
        Lection lection = null;

        try {
            validator.validate(id);
            lection = lectionSource.getLection(id);
        } catch (NotFoundException e) {
            Logger.error(getClass().getName(), e.getMessage(), e);
        }

        return lection;
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
}
