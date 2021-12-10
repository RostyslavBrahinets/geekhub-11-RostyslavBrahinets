package models;

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
}
