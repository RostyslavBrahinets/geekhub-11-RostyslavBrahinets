package org.geekhub.web.controllers;

import config.AppConfig;
import models.Lection;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import services.LectionService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = LectionController.LECTIONS_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class LectionController {
    public static final String LECTIONS_URL = "/lections";

    @GetMapping
    public List<Lection> findAllLection() {
        Optional<LectionService> lectionService = getLectionService();
        List<Lection> lections = List.of();

        if (lectionService.isPresent()) {
            lections = lectionService.get().getLections();
        }

        return lections;
    }

    @GetMapping("/{id}")
    public Lection findByIdLection(@PathVariable int id) {
        Optional<LectionService> lectionService = getLectionService();
        Lection lection = null;

        if (lectionService.isPresent()) {
            Optional<Lection> lectionOptional = lectionService.get().getLection(id);

            if (lectionOptional.isPresent()) {
                lection = lectionOptional.get();
            }
        }

        return lection;
    }

    @PostMapping
    public Lection saveLection(
        @RequestBody Lection lection,
        @RequestBody int lecturerId,
        @RequestBody int courseId
    ) {
        Optional<LectionService> lectionService = getLectionService();
        Lection createdLection = null;

        if (lectionService.isPresent()) {
            Optional<Lection> lectionOptional = lectionService.get()
                .addLection(lection, lecturerId, courseId);

            if (lectionOptional.isPresent()) {
                createdLection = lectionOptional.get();
            }
        }

        return createdLection;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLection(@PathVariable int id) {
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
