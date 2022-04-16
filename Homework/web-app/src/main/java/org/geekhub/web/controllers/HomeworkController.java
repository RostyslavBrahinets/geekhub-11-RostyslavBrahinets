package org.geekhub.web.controllers;

import config.AppConfig;
import models.HomeWork;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import services.HomeWorkService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = HomeworkController.HOMEWORKS_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class HomeworkController {
    public static final String HOMEWORKS_URL = "/homeworks";

    @GetMapping
    public List<HomeWork> findAllHomeWork() {
        Optional<HomeWorkService> homeWorkService = getHomeWorkService();
        List<HomeWork> homeWork = List.of();
        if (homeWorkService.isPresent()) {
            homeWork = homeWorkService.get().getHomeWorks();
        }
        return homeWork;
    }

    @GetMapping("/{id}")
    public Optional<HomeWork> findByIdHomeWork(@PathVariable int id) {
        Optional<HomeWorkService> homeWorkService = getHomeWorkService();
        Optional<HomeWork> homeWork = Optional.empty();
        if (homeWorkService.isPresent()) {
            homeWork = homeWorkService.get().getHomeWork(id);
        }
        return homeWork;
    }

    @PostMapping
    public ResponseEntity<HomeWork> saveHomeWork(
        @RequestBody HomeWork homeWork,
        @RequestBody int lectionId
    ) {
        Optional<HomeWorkService> homeWorkService = getHomeWorkService();
        Optional<HomeWork> createdHomeWork = Optional.empty();

        if (homeWorkService.isPresent()) {
            createdHomeWork = homeWorkService.get().addHomeWork(homeWork, lectionId);
        }

        ResponseEntity<HomeWork> entity = null;
        URI uri;

        if (createdHomeWork.isPresent()) {
            uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(HOMEWORKS_URL + "/{id}")
                .buildAndExpand(createdHomeWork.get().getId()).toUri();
            entity = ResponseEntity.created(uri).body(createdHomeWork.get());
        }

        return entity;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteHomeWork(@PathVariable int id) {
        Optional<HomeWorkService> homeWorkService = getHomeWorkService();
        homeWorkService.ifPresent(service -> service.deleteHomeWork(id));
    }

    private Optional<HomeWorkService> getHomeWorkService() {
        AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(AppConfig.class);
        HomeWorkService homeWorkService = applicationContext.getBean(HomeWorkService.class);
        return Optional.of(homeWorkService);
    }
}
