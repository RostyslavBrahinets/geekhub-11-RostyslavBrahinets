package config;

import db.DbConnectionProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import repository.*;

@Configuration
@Import(DbConfig.class)
public class RepositoryConfig {
    @Bean
    public ContactRepository contactRepository(DbConnectionProvider dbConnectionProvider) {
        return new ContactRepository(dbConnectionProvider);
    }

    @Bean
    public CourseRepository courseRepository(
        DbConnectionProvider dbConnectionProvider,
        PersonRepository personRepository
    ) {
        return new CourseRepository(dbConnectionProvider, personRepository);
    }

    @Bean
    public HomeWorkRepository homeWorkRepository(DbConnectionProvider dbConnectionProvider) {
        return new HomeWorkRepository(dbConnectionProvider);
    }

    @Bean
    public LectionRepository lectionRepository(
        DbConnectionProvider dbConnectionProvider,
        PersonRepository personRepository
    ) {
        return new LectionRepository(dbConnectionProvider, personRepository);
    }

    @Bean
    public PersonRepository personRepository(DbConnectionProvider dbConnectionProvider) {
        return new PersonRepository(dbConnectionProvider);
    }

    @Bean
    public ResourceRepository resourceRepository(DbConnectionProvider dbConnectionProvider) {
        return new ResourceRepository(dbConnectionProvider);
    }
}
