package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import repository.*;

import javax.sql.DataSource;

@Configuration
@Import(DatabaseConfig.class)
public class RepositoryConfig {
    @Bean
    public ContactRepository contactRepository(DataSource dataSource) {
        return new ContactRepository(dataSource);
    }

    @Bean
    public CourseRepository courseRepository(
        DataSource dataSource,
        PersonRepository personRepository
    ) {
        return new CourseRepository(dataSource, personRepository);
    }

    @Bean
    public HomeWorkRepository homeWorkRepository(DataSource dataSource) {
        return new HomeWorkRepository(dataSource);
    }

    @Bean
    public LectionRepository lectionRepository(
        DataSource dataSource,
        PersonRepository personRepository
    ) {
        return new LectionRepository(dataSource, personRepository);
    }

    @Bean
    public PersonRepository personRepository(DataSource dataSource) {
        return new PersonRepository(dataSource);
    }

    @Bean
    public ResourceRepository resourceRepository(DataSource dataSource) {
        return new ResourceRepository(dataSource);
    }
}
