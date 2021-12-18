import exceptions.NotFoundException;
import logger.Logger;
import use.UseCourseService;
import use.UseLectionService;

import java.util.Scanner;

public class ApplicationStarter {
    static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        UseLectionService useCommand = new UseLectionService();
        UseCourseService service = new UseCourseService();

        String message = """
                        
            1 - show all lections
            2 - add new lection
            3 - delete lection by number
            4 - show lection by number
            5 - show resources grouped by lecture
            6 - show home works grouped by lecture
            7 - exit
            8 - show all logs""";
        Logger.info(ApplicationStarter.class.getName(), message);

        while (true) {
            try {
                switch (getCommand()) {
                    case "1" -> useCommand.showAllLections();
                    case "2" -> useCommand.addNewLection();
                    case "3" -> useCommand.deleteLectionById();
                    case "4" -> useCommand.showLectionById();
                    case "5" -> useCommand.showResourcesGroupedByLecture();
                    case "6" -> useCommand.showHomeWorksGroupedByLecture();
                    case "7" -> {
                        service.closeScanner();
                        scanner.close();
                        System.exit(0);
                    }
                    case "8" -> Logger.showAllLogs();
                    default -> throw new NotFoundException("Command not found");
                }
            } catch (NotFoundException e) {
                Logger.error(ApplicationStarter.class.getName(), e.getMessage(), e);
            }
        }
    }

    private static String getCommand() {
        System.out.printf("%nInput command (1-6): ");
        return scanner.nextLine();
    }
}
