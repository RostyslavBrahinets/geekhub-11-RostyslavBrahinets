package org.geekhub.web.controllers;

import config.AppConfig;
import models.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import services.PersonService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = PersonController.PEOPLE_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonController {
    public static final String PEOPLE_URL = "/people";

    @GetMapping
    public List<Person> findAllPerson() {
        Optional<PersonService> personService = getPersonService();
        List<Person> people = List.of();

        if (personService.isPresent()) {
            people = personService.get().getPeople();
        }

        return people;
    }

    @GetMapping("/{id}")
    public Person findByIdPerson(@PathVariable int id) {
        Optional<PersonService> personService = getPersonService();
        Person person = null;

        if (personService.isPresent()) {
            Optional<Person> personOptional = personService.get().getPerson(id);

            if (personOptional.isPresent()) {
                person = personOptional.get();
            }
        }

        return person;
    }

    @PostMapping
    public Person savePerson(
        @RequestBody Person person,
        @RequestBody int courseId
    ) {
        Optional<PersonService> personService = getPersonService();
        Person createdPerson = null;

        if (personService.isPresent()) {
            Optional<Person> personOptional = personService.get().addPerson(person, courseId);

            if (personOptional.isPresent()) {
                createdPerson = personOptional.get();
            }
        }

        return createdPerson;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePerson(@PathVariable int id) {
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
