package sources;

import models.Lection;

import java.util.ArrayList;
import java.util.List;

public class LectionSource {
    private static LectionSource instance;
    private final List<Lection> lections;

    public LectionSource() {
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

    public static LectionSource getInstance() {
        if (instance == null) {
            instance = new LectionSource();
        }

        return instance;
    }
}
