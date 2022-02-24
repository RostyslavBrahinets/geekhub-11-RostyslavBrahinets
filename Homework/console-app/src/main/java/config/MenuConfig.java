package config;

import menu.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import services.*;

@Configuration
@Import(ServiceConfig.class)
public class MenuConfig {
    @Bean
    public LoggerMenu loggerMenu() {
        return new LoggerMenu();
    }

    @Bean
    public CoursesMenu coursesMenu(CourseService courseService) {
        return new CoursesMenu(courseService);
    }

    @Bean
    public HomeWorksMenu homeWorksMenu(HomeWorkService homeWorkService) {
        return new HomeWorksMenu(homeWorkService);
    }

    @Bean
    public LectionsMenu lectionsMenu(LectionService lectionService) {
        return new LectionsMenu(lectionService);
    }

    @Bean
    public PeopleMenu peopleMenu(PersonService personService) {
        return new PeopleMenu(personService);
    }

    @Bean
    public ResourcesMenu resourcesMenu(ResourceService resourceService) {
        return new ResourcesMenu(resourceService);
    }

    @Bean
    public MainMenu mainMenu(
        CoursesMenu coursesMenu,
        HomeWorksMenu homeWorksMenu,
        LectionsMenu lectionsMenu,
        PeopleMenu peopleMenu,
        ResourcesMenu resourcesMenu
    ) {
        return new MainMenu(
            coursesMenu,
            homeWorksMenu,
            lectionsMenu,
            peopleMenu,
            resourcesMenu
        );
    }
}
