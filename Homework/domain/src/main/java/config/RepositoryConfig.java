package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import repository.*;

@Configuration
@Import(DatabaseConfig.class)
public class RepositoryConfig {
    @Bean
    public ContactRepository contactRepository() {
        return new ContactRepository();
    }

    @Bean
    public CourseRepository courseRepository() {
        return new CourseRepository();
    }

    @Bean
    public HomeWorkRepository homeWorkRepository() {
        return new HomeWorkRepository();
    }

    @Bean
    public LectionRepository lectionRepository() {
        return new LectionRepository();
    }

    @Bean
    public PersonRepository personRepository() {
        return new PersonRepository();
    }

    @Bean
    public ResourceRepository resourceRepository() {
        return new ResourceRepository();
    }
}
