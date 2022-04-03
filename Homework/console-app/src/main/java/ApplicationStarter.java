import config.DatabaseConfig;
import config.MenuConfig;
import logger.Logger;
import menu.LoggerMenu;
import menu.MainMenu;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationStarter {
    static Logger logger;

    public static void main(String[] args) {
        setLogger();
        setDatabase();

        while (true) {
            try {
                AnnotationConfigApplicationContext applicationContext =
                    new AnnotationConfigApplicationContext(MenuConfig.class);
                MainMenu mainMenu = applicationContext.getBean(MainMenu.class);
                mainMenu.runMenu();
            } catch (Exception e) {
                logger.error(ApplicationStarter.class.getName(), e.getMessage(), e);
            }
        }
    }

    private static void setLogger() {
        boolean loggerNotSet = true;
        while (loggerNotSet) {
            try {
                AnnotationConfigApplicationContext applicationContext =
                    new AnnotationConfigApplicationContext(MenuConfig.class);
                LoggerMenu loggerMenu = applicationContext.getBean(LoggerMenu.class);
                loggerMenu.runMenu();
                logger = new Logger();
                logger.getLogs().forEach(System.out::println);
                loggerNotSet = false;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void setDatabase() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
            DatabaseConfig.class
        );

        Flyway flyway = (Flyway) context.getBean("flyway");
        flyway.migrate();
    }
}
