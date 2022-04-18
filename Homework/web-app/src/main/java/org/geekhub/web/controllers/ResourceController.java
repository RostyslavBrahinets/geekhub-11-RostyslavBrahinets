package org.geekhub.web.controllers;

import config.AppConfig;
import models.Resource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import services.ResourceService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = ResourceController.RESOURCES_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ResourceController {
    public static final String RESOURCES_URL = "/resources";

    @GetMapping
    public List<Resource> findAllResource() {
        Optional<ResourceService> resourceService = getResourceService();
        List<Resource> resources = List.of();

        if (resourceService.isPresent()) {
            resources = resourceService.get().getResources();
        }

        return resources;
    }

    @GetMapping("/{id}")
    public Resource findByIdResource(@PathVariable int id) {
        Optional<ResourceService> resourceService = getResourceService();
        Resource resource = null;

        if (resourceService.isPresent()) {
            Optional<Resource> resourceOptional = resourceService.get().getResource(id);

            if (resourceOptional.isPresent()) {
                resource = resourceOptional.get();
            }
        }

        return resource;
    }

    @PostMapping
    public Resource saveResource(
        @RequestBody Resource resource,
        @RequestBody int lectionId
    ) {
        Optional<ResourceService> resourceService = getResourceService();
        Resource createdResource = null;

        if (resourceService.isPresent()) {
            Optional<Resource> resourceOptional = resourceService.get()
                .addResource(resource, lectionId);

            if (resourceOptional.isPresent()) {
                createdResource = resourceOptional.get();
            }
        }

        return createdResource;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteResource(@PathVariable int id) {
        Optional<ResourceService> resourceService = getResourceService();
        resourceService.ifPresent(service -> service.deleteResource(id));
    }

    private Optional<ResourceService> getResourceService() {
        AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(AppConfig.class);
        ResourceService resourceService = applicationContext.getBean(ResourceService.class);

        return Optional.of(resourceService);
    }
}
