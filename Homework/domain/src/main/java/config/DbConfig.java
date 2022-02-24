package config;

import db.DbConnectionProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbConfig {
    @Bean
    public DbConnectionProvider dbConnectionProvider() {
        return new DbConnectionProvider();
    }
}
