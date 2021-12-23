package use;

import logger.Logger;
import models.HomeWork;
import models.Lection;
import models.Person;
import models.Resource;
import services.HomeWorkService;
import services.LectionService;
import services.PersonService;
import services.ResourceService;

import java.time.LocalDate;
import java.util.*;

public class UseLectionService {
    private final Scanner scanner = new Scanner(System.in);
    private final LectionService lectionService = new LectionService();
    private final UseResourceService useResourceService = new UseResourceService();
    private final UsePersonService usePersonService = new UsePersonService();
    private final UseHomeWorkService useHomeworkService = new UseHomeWorkService();

    public void showAllLections() {
        Optional<List<Lection>> lections = lectionService.getLections();
        if (lections.isPresent()) {
            for (Lection lection : lections.get()) {
                Logger.info(getClass().getName(),
                    String.format("%s: %s; %s; %s %s; %s%n",
                        lection.getName(),
                        lection.getDescribe(),
                        lection.getResources(),
                        lection.getLecturer().getFirstName(),
                        lection.getLecturer().getLastName(),
                        lection.getHomeWorks()
                    ));
            }
        }
    }

    public void addNewLection() {
        String name = getNameOfLection();
        String describe = getDescribeOfLection();
        useResourceService.addResources();
        Optional<Person> lecturer = getLecturer();
        useHomeworkService.addHomeWorks();
        ResourceService resourceService = new ResourceService();
        HomeWorkService homeWorkService = new HomeWorkService();
        Optional<List<Resource>> resources = resourceService.getResources();
        Optional<List<HomeWork>> homeWorks = homeWorkService.getHomeWorks();

        if (lecturer.isPresent() && resources.isPresent() && homeWorks.isPresent()) {
            lectionService.addLection(name, describe, resources.get(),
                lecturer.get(), homeWorks.get(), LocalDate.now());
        }



    }

    public void deleteLectionById() {
        lectionService.deleteLection(getIdOfLection());
    }

    public void showLectionById() {
        Optional<Lection> lection = lectionService.getLection(getIdOfLection());
        lection.ifPresent(value -> Logger.info(getClass().getName(),
            String.format("%s: %s; %s; %s %s; %s%n",
                value.getName(),
                value.getResources(),
                value.getLecturer().getFirstName(),
                value.getLecturer().getLastName(),
                value.getHomeWorks(),
                value.getCreationDate()
            )));
    }

    public void showResourcesGroupedByLecture() {
        Optional<List<Lection>> lections = lectionService.getLections();
        if (lections.isPresent()) {
            for (Lection lection : lections.get()) {
                Logger.info(getClass().getName(), String.format(
                    "%n%s: %s%n",
                    lection.getName(),
                    lectionService.getResourcesGroupedByLecture().get(lection)
                ));
            }
        }
    }

    public void showHomeWorksGroupedByLecture() {
        Optional<List<Lection>> lections = lectionService.getLections();
        if (lections.isPresent()) {
            for (Lection lection : lections.get()) {
                Logger.info(getClass().getName(), String.format(
                    "%n%s: %s%n",
                    lection.getName(),
                    lectionService.getHomeWorksGroupedByLecture().get(lection)
                ));
            }
        }
    }

    public void closeScanner() {
        useResourceService.closeScanner();
        useHomeworkService.closeScanner();
        scanner.close();
    }

    private String getNameOfLection() {
        System.out.print("\nInput name of lection: ");
        return scanner.nextLine();
    }

    private String getDescribeOfLection() {
        System.out.print("Input describe of lection: ");
        return scanner.nextLine();
    }

    private Optional<Person> getLecturer() {
        usePersonService.addPeople();
        return new PersonService().getPerson(0);
    }

    private int getIdOfLection() {
        System.out.print("Input id of lection: ");
        int id;

        try {
            id = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            id = -1;
            scanner.nextLine();
        }

        return id;
    }
}
