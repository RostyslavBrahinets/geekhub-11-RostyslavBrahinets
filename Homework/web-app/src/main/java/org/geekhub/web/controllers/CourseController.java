package org.geekhub.web.controllers;

import config.AppConfig;
import models.Course;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import services.CourseService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = CourseController.COURSES_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class CourseController {
    public static final String COURSES_URL = "/courses";

    @GetMapping
    public List<Course> findAllCourse() {
        Optional<CourseService> courseService = getCourseService();
        List<Course> courses = List.of();
        if (courseService.isPresent()) {
            courses = courseService.get().getCourses();
        }
        return courses;
    }

    @GetMapping("/{id}")
    public Optional<Course> findByIdCourse(@PathVariable int id) {
        Optional<CourseService> courseService = getCourseService();
        Optional<Course> course = Optional.empty();
        if (courseService.isPresent()) {
            course = courseService.get().getCourse(id);
        }
        return course;
    }

    @PostMapping
    public ResponseEntity<Course> saveCourse(@RequestBody Course course) {
        Optional<CourseService> courseService = getCourseService();
        Optional<Course> createdCourse = Optional.empty();

        if (courseService.isPresent()) {
            createdCourse = courseService.get().addCourse(course);
        }

        ResponseEntity<Course> entity = null;
        URI uri;

        if (createdCourse.isPresent()) {
            uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(COURSES_URL + "/{id}")
                .buildAndExpand(createdCourse.get().getId()).toUri();
            entity = ResponseEntity.created(uri).body(createdCourse.get());
        }

        return entity;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCourse(@PathVariable int id) {
        Optional<CourseService> courseService = getCourseService();
        courseService.ifPresent(service -> service.deleteCourse(id));
    }

    private Optional<CourseService> getCourseService() {
        AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(AppConfig.class);
        CourseService courseService = applicationContext.getBean(CourseService.class);
        return Optional.of(courseService);
    }
}
