package repository;

import models.Lection;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LectionRepository {
    private static LectionRepository instance;
    private final List<Lection> lections = new ArrayList<>();

    private LectionRepository() {
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

    public static LectionRepository getInstance() {
        if (instance == null) {
            instance = new LectionRepository();
        }

        return instance;
    }
}
