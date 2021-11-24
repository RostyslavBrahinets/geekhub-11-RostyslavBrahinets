public class Resource {
    private String name;
    private ResourceType type;
    private String data;

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
