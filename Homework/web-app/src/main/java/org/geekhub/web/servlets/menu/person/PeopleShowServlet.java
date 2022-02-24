package org.geekhub.web.servlets.menu.person;

import config.AppConfig;
import logger.Logger;
import models.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import services.PersonService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import static org.geekhub.web.servlets.SessionAttributes.COMMAND_SESSION_PARAMETER;
import static org.geekhub.web.servlets.SessionAttributes.USER_NAME_SESSION_PARAMETER;

@WebServlet(urlPatterns = "/menu/people/show")
public class PeopleShowServlet extends HttpServlet {
    @Override
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException {
        try {
            showMenu(request, response);
        } catch (SQLException e) {
            Logger logger = new Logger();
            logger.error(getClass().getSimpleName(), e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    private void showMenu(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException, SQLException {
        AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(AppConfig.class);
        PersonService personService =
            applicationContext.getBean(PersonService.class);

        List<Person> people = personService.getPeople();

        response.setContentType("text/html");
        try (PrintWriter writer = response.getWriter()) {
            writer.write("<html><head><title>People Show</title></head><body>");
            if (people.size() == 0) {
                showMenuIfPeopleNotFound(request, response);
                return;
            }
            showMenuIfPeopleFound(people, response);
            writer.write("</body></html>");
        }
    }

    private void showMenuIfPeopleNotFound(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException {
        try (PrintWriter writer = response.getWriter()) {
            writer.write("<h1>People not found!<h1>");

            HttpSession session = request.getSession();
            String userName = (String) session.getAttribute(USER_NAME_SESSION_PARAMETER);

            if (userName.equals("admin")) {
                writer.write("<h1>Do you want add new person?<h1>");
                writer.write("<form action=\"add\" method=\"get\">");
                writer.write("<input type=\"submit\" name=\"" + COMMAND_SESSION_PARAMETER
                    + "\" value=\"Add new\"></br></br>");
                writer.write("</form>");
            }
        }
    }

    private void showMenuIfPeopleFound(
        List<Person> people,
        HttpServletResponse response
    ) throws IOException {
        try (PrintWriter writer = response.getWriter()) {
            writer.write("<h1>People:</h1><ul>");
            for (Person person : people) {
                writer.write("<li>"
                    + person.getFirstName() + ": "
                    + person.getLastName() + ", "
                    + person.getContacts() + ", "
                    + person.getGitHubNickname() + ", "
                    + person.getRole()
                    + "</li>");
            }
            writer.write("<ul>");
        }
    }
}
