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

    public Resource(int id, String name, ResourceType type, String data) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resource resource = (Resource) o;
        return Objects.equals(name, resource.name)
            && type == resource.type
            && Objects.equals(data, resource.data);
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

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ResourceType getType() {
        return type;
    }

    public String getData() {
        return data;
    }

}
