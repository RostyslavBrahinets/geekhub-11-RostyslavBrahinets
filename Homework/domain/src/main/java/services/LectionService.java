package services;

import models.HomeWork;
import models.Lection;
import models.Person;
import models.Resource;
import repository.LectionRepository;
import validators.LectionValidator;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class LectionService {
    private final LectionRepository lectionSource = LectionRepository.getInstance();
    private final LectionValidator validator = new LectionValidator();

    public Optional<List<Lection>> getLections() {
        return Optional.ofNullable(lectionSource.getLections());
    }

    public void addLection(String name, String describe, List<Resource> resources, Person lecturer,
                           List<HomeWork> homeWorks) {
        validator.validate(name, describe, resources, lecturer, homeWorks);
        lectionSource.addLection(new Lection(name, describe, resources, lecturer, homeWorks, LocalDate.now()));
    }

    public void deleteLection(int id) {
        validator.validate(id);
        lectionSource.deleteLection(id);
    }

    public Optional<Lection> getLection(int id) {
        validator.validate(id);
        return Optional.ofNullable(lectionSource.getLection(id));
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
