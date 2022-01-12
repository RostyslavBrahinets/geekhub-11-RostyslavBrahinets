package services;

import models.HomeWork;
import models.Lection;
import models.Person;
import models.Resource;
import repository.LectionRepository;
import validators.LectionValidator;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class LectionService {
    private final LectionRepository lectionRepository = LectionRepository.getInstance();
    private final LectionValidator validator = new LectionValidator();

    public List<Lection> getLections() {
        return lectionRepository.getLections();
    }

    public void addLection(String name, String describe, List<Resource> resources, Person lecturer,
                           List<HomeWork> homeWorks) {
        validator.validate(name, describe, resources, lecturer, homeWorks);
        lectionRepository.addLection(new Lection(name, describe, resources, lecturer, homeWorks, LocalDate.now()));
    }

    public void deleteLection(int id) {
        validator.validate(id);
        lectionRepository.deleteLection(id);
    }

    public Optional<Lection> getLection(int id) {
        validator.validate(id);
        return lectionRepository.getLection(id);
    }

    public Map<String, List<Resource>> getResourcesGroupedByLecture() {
        return lectionRepository.getLections()
            .stream()
            .collect(Collectors.toMap(Lection::getName, Lection::getResources));
    }

    public Map<String, List<HomeWork>> getHomeWorksGroupedByLecture() {
        return lectionRepository.getLections()
            .stream()
            .collect(Collectors.toMap(Lection::getName, Lection::getHomeWorks));
    }

    public void sortLectionsByDateASC() {
        lectionRepository.sortLectionsByDateASC();
    }

    public void sortLectionsByDateDESC() {
        lectionRepository.sortLectionsByDateDESC();
    }
}
