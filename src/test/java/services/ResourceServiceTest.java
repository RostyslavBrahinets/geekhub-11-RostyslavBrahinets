package services;

import models.Resource;
import models.ResourceType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ResourceServiceTest {
    private static ResourceService resourceService;
    private static String name;
    private static String type;
    private static String data;

    @BeforeAll
    static void setUp() {
        resourceService = new ResourceService();
        name = "Resource";
        type = "URL";
        data = "Data";
        resourceService.addResource(name, type, data);
        resourceService.addResource(name, type, data);
    }

    @Test
    void getResources_DoNothing_WithoutError() {
        List<Resource> resources = new ArrayList<>();
        resources.add(new Resource(name, ResourceType.valueOf(type), data));
        resources.add(new Resource(name, ResourceType.valueOf(type), data));

        assertEquals(resources, resourceService.getResources());
    }

    @Test
    void addResource_DoNothing_WithoutError() {
        assertDoesNotThrow(() -> resourceService.addResource(name, type, data));
    }

    @Test
    void addResource_LoggingException_ForNameIsNull() {
        assertDoesNotThrow(() -> resourceService.addResource(null, type, data));
    }

    @Test
    void addResource_LoggingException_ForTypeIsNull() {
        assertDoesNotThrow(() -> resourceService.addResource(name, null, data));
    }

    @Test
    void addResource_LoggingException_ForDataIsNull() {
        assertDoesNotThrow(() -> resourceService.addResource(name, type, null));
    }

    @Test
    void addResource_LoggingException_ForNameIsEmpty() {
        assertDoesNotThrow(() -> resourceService.addResource("", type, data));
    }

    @Test
    void addResource_LoggingException_ForTypeIsEmpty() {
        assertDoesNotThrow(() -> resourceService.addResource(name, "", data));
    }

    @Test
    void addResource_LoggingException_ForDataIsEmpty() {
        assertDoesNotThrow(() -> resourceService.addResource(name, type, ""));
    }

    @Test
    void deleteResource_DoNothing_WithoutError() {
        assertDoesNotThrow(() -> resourceService.deleteResource(0));
    }

    @Test
    void deleteResource_LoggingException_ForIdIsLessThenExpected() {
        assertDoesNotThrow(() -> resourceService.deleteResource(-1));
    }

    @Test
    void deleteResource_LoggingException_ForIdIsMoreThenExpected() {
        assertDoesNotThrow(() -> resourceService.deleteResource(resourceService.getResources().size()));
    }

    @Test
    void getResource_DoNothing_WithoutError() {
        Resource resource = new Resource(name, ResourceType.valueOf(type), data);

        assertEquals(resource, resourceService.getResource(0));
    }

    @Test
    void getResource_ReturnNull_ForIdIsLessThenExpected() {
        assertNull(resourceService.getResource(-1));
    }

    @Test
    void getResource_ReturnNull_ForIdIsMoreThenExpected() {
        assertNull(resourceService.getResource(resourceService.getResources().size()));
    }
}