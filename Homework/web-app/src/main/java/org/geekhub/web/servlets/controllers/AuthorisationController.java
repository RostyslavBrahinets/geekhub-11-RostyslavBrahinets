package org.geekhub.web.servlets.controllers;

import config.DatabaseConfig;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

import static org.geekhub.web.servlets.SessionAttributes.USER_NAME_SESSION_PARAMETER;

@Controller
public class AuthorisationController {
    @GetMapping("/authorisation")
    public String authorisation() {
        setDatabase();
        return "authorisation";
    }

    @PostMapping("/authorisation")
    public String logIn(
        @ModelAttribute("userName") String userName,
        HttpSession session
    ) {
        List<String> users = List.of("admin", "user");
        if (users.contains(userName)) {
            session.setAttribute(USER_NAME_SESSION_PARAMETER, userName);
            return "redirect:/";
        } else {
            return "redirect:/authorisation";
        }
    }

    @DeleteMapping("/authorisation")
    public String logOut(HttpSession session) {
        session.setAttribute(USER_NAME_SESSION_PARAMETER, null);
        return "redirect:/authorisation";
    }

    private void setDatabase() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
            DatabaseConfig.class
        );

        Flyway flyway = (Flyway) context.getBean("flyway");
        flyway.migrate();
    }
}
