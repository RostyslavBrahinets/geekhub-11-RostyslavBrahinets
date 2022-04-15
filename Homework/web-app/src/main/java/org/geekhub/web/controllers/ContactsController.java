package org.geekhub.web.controllers;

import config.AppConfig;
import models.Contact;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import services.ContactsService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = ContactsController.CONTACTS_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ContactsController {
    public static final String CONTACTS_URL = "/contacts";

    @GetMapping
    public List<Contact> getAll() {
        Optional<ContactsService> contactsService = getContactsService();
        List<Contact> contacts = List.of();
        if (contactsService.isPresent()) {
            contacts = contactsService.get().getContacts();
        }
        return contacts;
    }

    @GetMapping("/{id}")
    public Optional<Contact> get(@PathVariable int id) {
        Optional<ContactsService> contactsService = getContactsService();
        Optional<Contact> contact = Optional.empty();
        if (contactsService.isPresent()) {
            contact = contactsService.get().getContact(id);
        }
        return contact;
    }

    @PostMapping
    public ResponseEntity<Contact> create(
        @RequestBody Contact contact,
        @RequestBody int personId
    ) {
        Optional<ContactsService> contactsService = getContactsService();
        Optional<Contact> createdContact = Optional.empty();

        if (contactsService.isPresent()) {
            createdContact = contactsService.get().addContact(contact, personId);
        }

        ResponseEntity<Contact> entity = null;
        URI uri;

        if (createdContact.isPresent()) {
            uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(CONTACTS_URL + "/{id}")
                .buildAndExpand(createdContact.get().getId()).toUri();
            entity = ResponseEntity.created(uri).body(createdContact.get());
        }

        return entity;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
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
