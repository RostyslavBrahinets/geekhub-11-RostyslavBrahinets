package org.geekhub.web.controllers;

import config.AppConfig;
import models.Contact;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import services.ContactsService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = ContactsController.CONTACTS_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ContactsController {
    public static final String CONTACTS_URL = "/contacts";

    @GetMapping
    public List<Contact> findAllContact() {
        Optional<ContactsService> contactsService = getContactsService();
        List<Contact> contacts = List.of();

        if (contactsService.isPresent()) {
            contacts = contactsService.get().getContacts();
        }

        return contacts;
    }

    @GetMapping("/{id}")
    public Contact findByIdContact(@PathVariable int id) {
        Optional<ContactsService> contactsService = getContactsService();
        Contact contact = null;

        if (contactsService.isPresent()) {
            Optional<Contact> contactOptional = contactsService.get().getContact(id);

            if (contactOptional.isPresent()) {
                contact = contactOptional.get();
            }
        }

        return contact;
    }

    @PostMapping
    public Contact saveContact(
        @RequestBody Contact contact,
        @RequestBody int personId
    ) {
        Optional<ContactsService> contactsService = getContactsService();
        Contact createdContact = null;

        if (contactsService.isPresent()) {
            Optional<Contact> contactOptional = contactsService.get().addContact(contact, personId);

            if (contactOptional.isPresent()) {
                createdContact = contactOptional.get();
            }
        }

        return createdContact;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContact(@PathVariable int id) {
        Optional<ContactsService> contactsService = getContactsService();
        contactsService.ifPresent(service -> service.deleteContact(id));
    }

    private Optional<ContactsService> getContactsService() {
        AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(AppConfig.class);
        ContactsService contactsService = applicationContext.getBean(ContactsService.class);

        return Optional.of(contactsService);
    }
}
