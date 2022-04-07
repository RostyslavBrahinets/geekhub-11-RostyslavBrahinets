package org.geekhub.web.servlets.controllers.menu;

import config.AppConfig;
import models.Contact;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import services.ContactService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

import static org.geekhub.web.servlets.SessionAttributes.USER_NAME_SESSION_PARAMETER;

@Controller
@RequestMapping("/menu/contacts")
public class ContactsController {
    @GetMapping("")
    public String index(
        HttpSession session,
        Model model
    ) {
        String userName = (String) session.getAttribute(USER_NAME_SESSION_PARAMETER);
        userName = "admin";

        model.addAttribute("userName", userName);

        return "menu/contacts/index";
    }

    @GetMapping("/{command}")
    public String doCommand(
        @PathVariable("command") String command,
        Model model
    ) {
        return switch (command) {
            case "show-all" -> getViewForShowAll(model);
            case "add" -> "menu/contacts/add";
            case "delete" -> "menu/contacts/delete";
            case "show" -> "menu/contacts/show";
            default -> "menu/contacts/index";
        };
    }

    @PostMapping("/delete")
    public String delete(
        @ModelAttribute("id") int id
    ) {
        AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(AppConfig.class);
        ContactService contactService =
            applicationContext.getBean(ContactService.class);

        contactService.deleteContact(id);

        return "redirect:/menu/contacts";
    }

    @PostMapping("/show")
    public String show(
        @ModelAttribute("id") int id,
        Model model
    ) {
        AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(AppConfig.class);
        ContactService contactService =
            applicationContext.getBean(ContactService.class);

        Optional<Contact> contact = contactService.getContact(id);
        contact.ifPresent(value -> model.addAttribute("contact", value));

        return "menu/contacts/show-contacts";
    }

    private String getViewForShowAll(Model model) {
        AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(AppConfig.class);
        ContactService contactService =
            applicationContext.getBean(ContactService.class);

        List<Contact> contacts = contactService.getContacts();
        model.addAttribute("contacts", contacts);
        return "menu/contacts/show-all";
    }
}
