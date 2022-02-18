package models;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public final class Resource implements Serializable {
    @Serial
    private static final long serialVersionUID = 5L;
    private int id;
    private String name;
    private ResourceType type;
    private String data;

    public Resource() {
    }

    public Resource(
        String name,
        ResourceType type,
        String data
    ) {
        this.name = name;
        this.type = type;
        this.data = data;
    }

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

    public int id() {
        return id;
    }

    public String name() {
        return name;
    }

    public ResourceType type() {
        return type;
    }

    public String data() {
        return data;
    }

}
