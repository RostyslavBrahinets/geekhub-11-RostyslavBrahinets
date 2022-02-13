package models;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public record Resource(
    String name,
    ResourceType type,
    String data
) implements Serializable {
    @Serial
    private static final long serialVersionUID = 5L;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resource resource = (Resource) o;
        return Objects.equals(name, resource.name) && type == resource.type && Objects.equals(data, resource.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, data);
    }

    @Override
    public String toString() {
        return "Resource {"
            + "name: '" + name + '\''
            + ", type: " + type
            + ", data: '" + data + '\''
            + '}';
    }
}
