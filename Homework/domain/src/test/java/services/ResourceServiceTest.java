package services;

import exceptions.InvalidArgumentException;
import exceptions.NotFoundException;
import exceptions.ValidationException;
import models.Resource;
import models.ResourceType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.ResourcesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ResourceServiceTest {
    private ResourceService resourceService;
    private static String name;
    private static String type;
    private static String data;

    @BeforeAll
    static void setDataInRepository() {
        ResourcesRepository resourcesSource = ResourcesRepository.getInstance();
        name = "Resource";
        type = "URL";
        data = "Data";
        resourcesSource.addResource(
            new Resource(name, ResourceType.valueOf(type), data)
        );
        resourcesSource.addResource(
            new Resource(name, ResourceType.valueOf(type), data)
        );
    }

    @BeforeEach
    void setUp() {
        resourceService = new ResourceService();
    }

    @Test
    void getResources_DoNothing_WithoutError() {
        List<Resource> resources = new ArrayList<>();
        resources.add(
            new Resource(name, ResourceType.valueOf(type), data)
        );
        resources.add(
            new Resource(name, ResourceType.valueOf(type), data)
        );

        assertEquals(resources, resourceService.getResources());
    }

    @Test
    void addResource_DoNothing_WithoutError() {
        assertDoesNotThrow(
            () -> resourceService.addResource(name, type, data)
        );
    }

    @Test
    void addResource_ThrowValidationException_ForNameIsNull() {
        assertThrows(
            ValidationException.class,
            () -> resourceService.addResource(null, type, data)
        );
    }

    @Test
    void addResource_ThrowInvalidArgumentException_ForTypeIsNull() {
        assertThrows(
            InvalidArgumentException.class,
            () -> resourceService.addResource(name, null, data)
        );
    }

    @Test
    void addResource_ThrowValidationException_ForDataIsNull() {
        assertThrows(
            ValidationException.class,
            () -> resourceService.addResource(name, type, null)
        );
    }

    @Test
    void addResource_ThrowValidationException_ForNameIsEmpty() {
        assertThrows(
            ValidationException.class,
            () -> resourceService.addResource("", type, data)
        );
    }

    @Test
    void addResource_ThrowInvalidArgumentException_ForTypeIsEmpty() {
        assertThrows(
            InvalidArgumentException.class,
            () -> resourceService.addResource(name, "", data)
        );
    }

    @Test
    void addResource_ThrowValidationException_ForDataIsEmpty() {
        assertThrows(
            ValidationException.class,
            () -> resourceService.addResource(name, type, "")
        );
    }

    @Test
    void addResource_ThrowInvalidArgumentException_ForTypeIsInvalid() {
        assertThrows(
            InvalidArgumentException.class,
            () -> resourceService.addResource(name, "TYPE", data)
        );
    }

    @Test
    void deleteResource_DoNothing_WithoutError() {
        assertDoesNotThrow(
            () -> resourceService.deleteResource(0)
        );
    }

    @Test
    void deleteResource_ThrowNotFoundException_ForIdIsLessThenExpected() {
        assertThrows(
            NotFoundException.class,
            () -> resourceService.deleteResource(-1)
        );
    }

    @Test
    void deleteResource_ThrowNotFoundException_ForIdIsMoreThenExpected() {
        List<Resource> resources = resourceService.getResources();
        int size = resources.size();

        assertThrows(
            NotFoundException.class,
            () -> resourceService.deleteResource(size)
        );
    }

    @Test
    void getResource_DoNothing_WithoutError() {
        Resource expectedResource = new Resource(name, ResourceType.valueOf(type), data);
        Optional<Resource> resource = resourceService.getResource(0);

        Resource actualResource = null;
        if (resource.isPresent()) {
            actualResource = resource.get();
        }

        assertEquals(expectedResource, actualResource);
    }

    @Test
    void getResource_ThrowNotFoundException_ForIdIsLessThenExpected() {
        assertThrows(
            NotFoundException.class,
            () -> resourceService.getResource(-1)
        );
    }

    @Test
    void getResource_ThrowNotFoundException_ForIdIsMoreThenExpected() {
        List<Resource> resources = resourceService.getResources();
        int size = resources.size();

        assertThrows(
            NotFoundException.class,
            () -> resourceService.getResource(size)
        );
    }
}