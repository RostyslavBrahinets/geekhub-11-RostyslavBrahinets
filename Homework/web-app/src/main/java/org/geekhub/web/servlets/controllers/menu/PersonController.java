package org.geekhub.web.servlets.controllers.menu;

import config.AppConfig;
import models.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import services.PersonService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

import static org.geekhub.web.servlets.SessionAttributes.USER_NAME_SESSION_PARAMETER;

@Controller
@RequestMapping("/menu/person")
public class PersonController {
    @GetMapping("")
    public String index(
        HttpSession session,
        Model model
    ) {
        String userName = (String) session.getAttribute(USER_NAME_SESSION_PARAMETER);
        userName = "admin";

        model.addAttribute("userName", userName);

        return "menu/person/index";
    }

    @GetMapping("/{command}")
    public String doCommand(
        @PathVariable("command") String command,
        Model model
    ) {
        return switch (command) {
            case "show-all" -> getViewForShowAll(model);
            case "add" -> "menu/person/add";
            case "delete" -> "menu/person/delete";
            case "show" -> "menu/person/show";
            default -> "menu/person/index";
        };
    }

    @PostMapping("/add")
    public String show(
        @ModelAttribute("firstName") String firstName,
        @ModelAttribute("lastName") String lastName,
        @ModelAttribute("nickName") String nickName,
        @ModelAttribute("role") String role,
        @ModelAttribute("courseId") int courseId
    ) {
        AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(AppConfig.class);
        PersonService personService =
            applicationContext.getBean(PersonService.class);

        personService.addPerson(firstName, lastName, nickName, role, courseId);

        return "redirect:/menu/person";
    }

    @PostMapping("/delete")
    public String delete(
        @ModelAttribute("id") int id
    ) {
        AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(AppConfig.class);
        PersonService personService =
            applicationContext.getBean(PersonService.class);

        personService.deletePerson(id);

        return "redirect:/menu/person";
    }

    @PostMapping("/show")
    public String show(
        @ModelAttribute("id") int id,
        Model model
    ) {
        AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(AppConfig.class);
        PersonService personService =
            applicationContext.getBean(PersonService.class);

        Optional<Person> person = personService.getPerson(id);
        person.ifPresent(value -> model.addAttribute("person", value));

        return "menu/person/show-by-id";
    }

    private String getViewForShowAll(Model model) {
        AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(AppConfig.class);
        PersonService personService =
            applicationContext.getBean(PersonService.class);

        List<Person> people = personService.getPeople();
        model.addAttribute("people", people);
        return "menu/person/show-all";
    }
}
