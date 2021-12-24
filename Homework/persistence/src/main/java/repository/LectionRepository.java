package repository;

import models.Lection;

import java.util.ArrayList;
import java.util.List;

public class LectionRepository {
    private static LectionRepository instance;
    private final List<Lection> lections;

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

    public Lection getLection(int id) {
        return lections.get(id);
    }

    public static LectionRepository getInstance() {
        if (instance == null) {
            instance = new LectionRepository();
        }

        return instance;
    }
}
