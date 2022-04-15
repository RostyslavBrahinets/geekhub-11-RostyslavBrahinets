package org.geekhub.web.controllers;

import config.AppConfig;
import models.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import services.PersonService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = PersonController.PEOPLE_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonController {
    public static final String PEOPLE_URL = "/people";

    @GetMapping
    public List<Person> getAll() {
        Optional<PersonService> personService = getPersonService();
        List<Person> people = List.of();
        if (personService.isPresent()) {
            people = personService.get().getPeople();
        }
        return people;
    }

    @GetMapping("/{id}")
    public Optional<Person> get(@PathVariable int id) {
        Optional<PersonService> personService = getPersonService();
        Optional<Person> person = Optional.empty();
        if (personService.isPresent()) {
            person = personService.get().getPerson(id);
        }
        return person;
    }

    @PostMapping
    public ResponseEntity<Person> create(
        @RequestBody Person person,
        @RequestBody int courseId
    ) {
        Optional<PersonService> personService = getPersonService();
        Optional<Person> createdPerson = Optional.empty();

        if (personService.isPresent()) {
            createdPerson = personService.get().addPerson(person, courseId);
        }

        ResponseEntity<Person> entity = null;
        URI uri;

        if (createdPerson.isPresent()) {
            uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(PEOPLE_URL + "/{id}")
                .buildAndExpand(createdPerson.get().getId()).toUri();
            entity = ResponseEntity.created(uri).body(createdPerson.get());
        }

        return entity;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        Optional<PersonService> personService = getPersonService();
        personService.ifPresent(service -> service.deletePerson(id));
    }

    private Optional<PersonService> getPersonService() {
        AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(AppConfig.class);
        PersonService personService = applicationContext.getBean(PersonService.class);
        return Optional.of(personService);
    }
}
