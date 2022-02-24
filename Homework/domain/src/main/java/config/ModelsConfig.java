package config;

import models.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelsConfig {
    @Bean
    public Contact contact() {
        return new Contact();
    }

    @Bean
    public Course course() {
        return new Course();
    }

    @Bean
    public HomeWork homeWork() {
        return new HomeWork();
    }

    @Bean
    public Lection lection() {
        return new Lection();
    }

    @Bean
    public Person person() {
        return new Person();
    }

    @Bean
    public Resource resource() {
        return new Resource();
    }
}
