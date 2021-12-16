package models;

import java.util.Objects;

public class Resource {
    private final String name;
    private final ResourceType type;
    private final String data;

    public Resource(String name, ResourceType type, String data) {
        this.name = name;
        this.type = type;
        this.data = data;
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
