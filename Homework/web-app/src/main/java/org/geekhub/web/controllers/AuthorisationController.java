package org.geekhub.web.controllers;

import config.DatabaseConfig;
import org.flywaydb.core.Flyway;
import org.geekhub.web.SessionAttributes;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AuthorisationController {
    @GetMapping("/authorisation")
    public String authorisation() {
        return "authorisation";
    }

    @PostMapping("/authorisation")
    public String logIn(
        @ModelAttribute("userName") String userName,
        HttpSession session
    ) {
        List<String> users = List.of("admin", "user");
        if (users.contains(userName)) {
            session.setAttribute(SessionAttributes.USER_NAME_SESSION_PARAMETER, userName);
            return "redirect:/";
        } else {
            return "redirect:/authorisation";
        }
    }

    @DeleteMapping("/authorisation")
    public String logOut(HttpSession session) {
        session.setAttribute(SessionAttributes.USER_NAME_SESSION_PARAMETER, null);
        return "redirect:/authorisation";
    }
}
