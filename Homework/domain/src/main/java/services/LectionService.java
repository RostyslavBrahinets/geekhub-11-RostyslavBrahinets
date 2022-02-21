package services;

import models.HomeWork;
import models.Lection;
import models.Resource;
import repository.LectionRepository;
import validators.LectionValidator;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class LectionService {
    private final LectionRepository lectionRepository;
    private final LectionValidator validator;

    public LectionService() throws SQLException {
        lectionRepository = LectionRepository.getInstance();
        validator = new LectionValidator();
    }

    public List<Lection> getLections() throws SQLException, IOException {
        return lectionRepository.getLections();
    }

    public void addLection(
        String name,
        String describe,
        int lecturerId,
        int courseId
    ) throws SQLException, IOException {
        validator.validate(name, describe);
        lectionRepository.addLection(
            new Lection(
                name,
                describe,
                LocalDate.now()),
            lecturerId,
            courseId
        );
    }

    public void deleteLection(int id) throws SQLException, IOException {
        validator.validate(id);
        lectionRepository.deleteLection(id);
    }

    public Optional<Lection> getLection(int id) throws SQLException, IOException {
        validator.validate(id);
        return lectionRepository.getLection(id);
    }

    public Map<String, List<Resource>> getResourcesGroupedByLecture() throws SQLException, IOException {
        return lectionRepository.getLections()
            .stream()
            .collect(Collectors.toMap(Lection::getName, Lection::getResources));
    }

    public Map<String, List<HomeWork>> getHomeWorksGroupedByLecture() throws SQLException, IOException {
        return lectionRepository.getLections()
            .stream()
            .collect(Collectors.toMap(Lection::getName, Lection::getHomeWorks));
    }

    public List<Lection> getLectionsSortedByDateASC() throws SQLException, IOException {
        List<Lection> lections = lectionRepository.getLections();
        List<Lection> sortedLections;

        sortedLections = lections.stream()
            .sorted(Comparator.comparing(Lection::getCreationDate))
            .toList();

        return sortedLections;
    }

    public List<Lection> getLectionsSortedByDateDESC() throws SQLException, IOException {
        List<Lection> lections = lectionRepository.getLections();
        List<Lection> sortedLections;

        sortedLections = lections.stream()
            .sorted(Comparator.comparing(Lection::getCreationDate).reversed())
            .toList();

        return sortedLections;
    }
}
