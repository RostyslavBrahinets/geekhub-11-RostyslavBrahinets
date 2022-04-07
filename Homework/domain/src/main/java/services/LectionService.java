package services;

import models.HomeWork;
import models.Lection;
import models.Resource;
import repository.LectionRepository;
import validators.LectionValidator;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class LectionService {
    private final LectionRepository lectionRepository;
    private final LectionValidator validator;

    public LectionService(
        LectionRepository lectionRepository,
        LectionValidator validator
    ) {
        this.lectionRepository = lectionRepository;
        this.validator = validator;
    }

    public List<Lection> getLections() {
        return lectionRepository.getLections();
    }

    public void addLection(String name, String describe, int lecturerId, int courseId) {
        validator.validate(name, describe);
        lectionRepository.addLection(
            new Lection(name, describe, LocalDate.now()),
            lecturerId,
            courseId
        );
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

    public List<Lection> getLectionsSortedByDateAsc() {
        List<Lection> lections = lectionRepository.getLections();
        List<Lection> sortedLections;

        sortedLections = lections.stream()
            .sorted(Comparator.comparing(Lection::getCreationDate))
            .toList();

        return sortedLections;
    }

    public List<Lection> getLectionsSortedByDateDesc() {
        List<Lection> lections = lectionRepository.getLections();
        List<Lection> sortedLections;

        sortedLections = lections.stream()
            .sorted(Comparator.comparing(Lection::getCreationDate).reversed())
            .toList();

        return sortedLections;
    }
}
