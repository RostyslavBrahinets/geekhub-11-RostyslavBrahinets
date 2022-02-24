package config;

import db.DbConnectionProvider;
import db.DbStarter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbConfig {
    @Bean
    public DbConnectionProvider dbConnectionProvider() {
        return new DbConnectionProvider();
    }

    @Bean
    public DbStarter dbStarter(DbConnectionProvider dbConnectionProvider) {
        return new DbStarter(dbConnectionProvider);
    }
}
