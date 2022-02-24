package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import repository.*;
import validators.*;

@Configuration
@Import(RepositoryConfig.class)
public class ValidatorConfig {
    @Bean
    public ContactValidator contactValidator(ContactRepository contactRepository) {
        return new ContactValidator(contactRepository);
    }

    @Bean
    public CourseValidator courseValidator(CourseRepository courseRepository) {
        return new CourseValidator(courseRepository);
    }

    @Bean
    public HomeWorkValidator homeWorkValidator(HomeWorkRepository homeWorkRepository) {
        return new HomeWorkValidator(homeWorkRepository);
    }

    @Bean
    public LectionValidator lectionValidator(LectionRepository lectionRepository) {
        return new LectionValidator(lectionRepository);
    }

    @Bean
    public PersonValidator personValidator(PersonRepository personRepository) {
        return new PersonValidator(personRepository);
    }

    @Bean
    public ResourceValidator resourceValidator(ResourceRepository resourceRepository) {
        return new ResourceValidator(resourceRepository);
    }
}
