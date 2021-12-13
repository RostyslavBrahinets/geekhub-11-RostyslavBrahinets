import exceptions.CommandNotFoundException;
import logger.Logger;
import use.UseLectionService;

import java.util.Scanner;

public class ApplicationStarter {
    static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        UseLectionService useCommand = new UseLectionService();

        String message = "\n1 - show all lections\n2 - add new lection\n3 - delete lection by number\n" +
            "4 - show lection by number\n5 - exit\n6 - show all logs";
        Logger.info(ApplicationStarter.class.getName(), message);

        while (true) {
            try {
                switch (getCommand()) {
                    case "1" -> useCommand.showAllLections();
                    case "2" -> useCommand.addNewLection();
                    case "3" -> useCommand.deleteLectionByNumber();
                    case "4" -> useCommand.showLectionByNumber();
                    case "5" -> {
                        scanner.close();
                        useCommand.exit();
                    }
                    case "6" -> Logger.showAllLogs();
                    default -> throw new CommandNotFoundException("Command Not Found");
                }
            } catch (CommandNotFoundException e) {
                message = "Invalid command!";
                Logger.error(ApplicationStarter.class.getName(), message, e);
            }
        }
    }

    private static String getCommand() {
        System.out.printf("%nInput command (1-6): ");
        return scanner.nextLine();
    }
}
