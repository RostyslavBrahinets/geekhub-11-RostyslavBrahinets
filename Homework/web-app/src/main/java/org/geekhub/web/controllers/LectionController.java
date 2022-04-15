package org.geekhub.web.controllers;

import config.AppConfig;
import models.Lection;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import services.LectionService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = LectionController.LECTIONS_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class LectionController {
    public static final String LECTIONS_URL = "/lections";

    @GetMapping
    public List<Lection> getAll() {
        Optional<LectionService> lectionService = getLectionService();
        List<Lection> lections = List.of();
        if (lectionService.isPresent()) {
            lections = lectionService.get().getLections();
        }
        return lections;
    }

    @GetMapping("/{id}")
    public Optional<Lection> get(@PathVariable int id) {
        Optional<LectionService> lectionService = getLectionService();
        Optional<Lection> lection = Optional.empty();
        if (lectionService.isPresent()) {
            lection = lectionService.get().getLection(id);
        }
        return lection;
    }

    @PostMapping
    public ResponseEntity<Lection> create(
        @RequestBody Lection lection,
        @RequestBody int lecturerId,
        @RequestBody int courseId
    ) {
        Optional<LectionService> lectionService = getLectionService();
        Optional<Lection> createdLection = Optional.empty();

        if (lectionService.isPresent()) {
            createdLection = lectionService.get().addLection(lection, lecturerId, courseId);
        }

        ResponseEntity<Lection> entity = null;
        URI uri;

        if (createdLection.isPresent()) {
            uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(LECTIONS_URL + "/{id}")
                .buildAndExpand(createdLection.get().getId()).toUri();
            entity = ResponseEntity.created(uri).body(createdLection.get());
        }

        return entity;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        Optional<LectionService> lectionService = getLectionService();
        lectionService.ifPresent(service -> service.deleteLection(id));
    }

    private Optional<LectionService> getLectionService() {
        AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(AppConfig.class);
        LectionService lectionService = applicationContext.getBean(LectionService.class);
        return Optional.of(lectionService);
    }
}
