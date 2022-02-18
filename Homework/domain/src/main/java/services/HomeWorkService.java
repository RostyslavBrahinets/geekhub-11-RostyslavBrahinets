package services;

import models.HomeWork;
import repository.HomeWorkRepository;
import validators.HomeWorkValidator;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class HomeWorkService {
    private final HomeWorkRepository homeWorkRepository = HomeWorkRepository.getInstance();
    private final HomeWorkValidator validator = new HomeWorkValidator();

    public HomeWorkService() throws SQLException, IOException {
    }

    public List<HomeWork> getHomeWorks() throws SQLException {
        return homeWorkRepository.getHomeWorks();
    }

    public void addHomeWork(String task, LocalDateTime deadline) throws SQLException {
        validator.validate(task, deadline);
        homeWorkRepository.addHomeWork(new HomeWork(task, deadline));
    }

    public void deleteHomeWork(int id) throws SQLException, IOException {
        validator.validate(id);
        homeWorkRepository.deleteHomeWork(id);
    }

    public Optional<HomeWork> getHomeWork(int id) throws SQLException, IOException {
        validator.validate(id);
        return homeWorkRepository.getHomeWork(id);
    }
}
