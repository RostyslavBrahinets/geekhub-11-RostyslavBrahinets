package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import repository.*;
import services.*;
import validators.*;

import java.sql.SQLException;

@Configuration
@Import({RepositoryConfig.class, ValidatorConfig.class})
public class ServiceConfig {
    @Bean
    public ContactsService contactService(
        ContactRepository contactRepository,
        ContactValidator contactValidator
    ) {
        return new ContactsService(contactRepository, contactValidator);
    }

    @Bean
    public CourseService courseService(
        CourseRepository courseRepository,
        CourseValidator courseValidator
    ) throws SQLException {
        return new CourseService(courseRepository, courseValidator);
    }

    @Bean
    public HomeWorkService homeWorkService(
        HomeWorkRepository homeWorkRepository,
        HomeWorkValidator homeWorkValidator
    ) throws SQLException {
        return new HomeWorkService(homeWorkRepository, homeWorkValidator);
    }

    @Bean
    public LectionService lectionService(
        LectionRepository lectionRepository,
        LectionValidator lectionValidator
    ) throws SQLException {
        return new LectionService(lectionRepository, lectionValidator);
    }

    @Bean
    public PersonService personService(
        PersonRepository personRepository,
        PersonValidator personValidator
    ) throws SQLException {
        return new PersonService(personRepository, personValidator);
    }

    @Bean
    public ResourceService resourceService(
        ResourceRepository resourceRepository,
        ResourceValidator resourceValidator
    ) throws SQLException {
        return new ResourceService(resourceRepository, resourceValidator);
    }
}
