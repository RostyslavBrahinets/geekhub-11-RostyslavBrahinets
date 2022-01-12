package repository;

import models.Lection;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class LectionRepository {
    private static LectionRepository instance;
    private List<Lection> lections;

    public LectionRepository() {
        lections = new ArrayList<>();
    }

    public List<Lection> getLections() {
        return lections;
    }

    public void addLection(Lection lection) {
        lections.add(lection);
    }

    public void deleteLection(int id) {
        lections.remove(id);
    }

    public Optional<Lection> getLection(int id) {
        return Optional.ofNullable(lections.get(id));
    }

    public void sortLectionsByDateASC() {
        lections = lections.stream()
            .sorted(Comparator.comparing(Lection::getCreationDate))
            .toList();
    }

    public void sortLectionsByDateDESC() {
        lections = lections.stream()
            .sorted(Comparator.comparing(Lection::getCreationDate).reversed())
            .toList();
    }

    public static LectionRepository getInstance() {
        if (instance == null) {
            instance = new LectionRepository();
        }

        return instance;
    }
}
