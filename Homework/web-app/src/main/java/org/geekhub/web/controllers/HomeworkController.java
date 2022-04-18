package org.geekhub.web.controllers;

import config.AppConfig;
import models.HomeWork;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import services.HomeWorkService;

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
    public HomeWork findByIdHomeWork(@PathVariable int id) {
        Optional<HomeWorkService> homeWorkService = getHomeWorkService();
        HomeWork homeWork = null;

        if (homeWorkService.isPresent()) {
            Optional<HomeWork> homeWorkOptional = homeWorkService.get().getHomeWork(id);

            if (homeWorkOptional.isPresent()) {
                homeWork = homeWorkOptional.get();
            }
        }

        return homeWork;
    }

    @PostMapping
    public HomeWork saveHomeWork(
        @RequestBody HomeWork homeWork,
        @RequestBody int lectionId
    ) {
        Optional<HomeWorkService> homeWorkService = getHomeWorkService();
        HomeWork createdHomeWork = null;

        if (homeWorkService.isPresent()) {
            Optional<HomeWork> homeWorkOptional = homeWorkService.get().addHomeWork(homeWork, lectionId);

            if (homeWorkOptional.isPresent()) {
                createdHomeWork = homeWorkOptional.get();
            }
        }

        return createdHomeWork;
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
