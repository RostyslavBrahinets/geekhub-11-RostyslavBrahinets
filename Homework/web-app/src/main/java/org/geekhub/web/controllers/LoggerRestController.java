package org.geekhub.web.controllers;

import exceptions.NotFoundException;
import logger.Logger;
import logger.LoggerStorageFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = LoggerRestController.LOGGER_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class LoggerRestController {
    public static final String LOGGER_URL = "/logger";

    @GetMapping("/logs")
    public List<String> findAllLog() {
        Logger logger = new Logger();
        return logger.getLogs();
    }

    @GetMapping("/logs/{id}")
    public String findByIdLog(@PathVariable int id) {
        Logger logger = new Logger();
        return logger.getLogs().get(id);
    }

    @PostMapping("/settings")
    public String settings(
        @ModelAttribute("loggerStorage") String loggerStorage
    ) {
        switch (loggerStorage) {
            case "In Memory" -> LoggerStorageFactory.setStorage("memory");
            case "In File" -> LoggerStorageFactory.setStorage("file");
            case "In Memory And File" -> LoggerStorageFactory.setStorage("memory_file");
            default -> throw new NotFoundException("Logger Storage Not Found");
        }

        return loggerStorage;
    }
}
