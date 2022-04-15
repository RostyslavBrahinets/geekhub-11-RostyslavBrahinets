package org.geekhub.web.controllers;

import config.AppConfig;
import models.Resource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import services.ResourceService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = ResourceController.RESOURCES_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ResourceController {
    public static final String RESOURCES_URL = "/resources";

    @GetMapping
    public List<Resource> getAll() {
        Optional<ResourceService> resourceService = getResourceService();
        List<Resource> resources = List.of();
        if (resourceService.isPresent()) {
            resources = resourceService.get().getResources();
        }
        return resources;
    }

    @GetMapping("/{id}")
    public Optional<Resource> get(@PathVariable int id) {
        Optional<ResourceService> resourceService = getResourceService();
        Optional<Resource> resource = Optional.empty();
        if (resourceService.isPresent()) {
            resource = resourceService.get().getResource(id);
        }
        return resource;
    }

    @PostMapping
    public ResponseEntity<Resource> create(
        @RequestBody Resource resource,
        @RequestBody int lectionId
    ) {
        Optional<ResourceService> resourceService = getResourceService();
        Optional<Resource> createdResource = Optional.empty();

        if (resourceService.isPresent()) {
            createdResource = resourceService.get().addResource(resource, lectionId);
        }

        ResponseEntity<Resource> entity = null;
        URI uri;

        if (createdResource.isPresent()) {
            uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(RESOURCES_URL + "/{id}")
                .buildAndExpand(createdResource.get().getId()).toUri();
            entity = ResponseEntity.created(uri).body(createdResource.get());
        }

        return entity;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
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
