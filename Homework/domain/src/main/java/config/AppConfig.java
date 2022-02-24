package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
    DbConfig.class,
    ModelsConfig.class,
    RepositoryConfig.class,
    ServiceConfig.class,
    ValidatorConfig.class
})
public class AppConfig {

}
