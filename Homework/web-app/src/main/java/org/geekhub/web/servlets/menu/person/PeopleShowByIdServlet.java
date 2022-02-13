package org.geekhub.web.servlets.menu.person;

import exceptions.NotFoundException;
import logger.Logger;
import models.Person;
import org.geekhub.web.servlets.menu.MenuCommand;
import services.PersonService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import static org.geekhub.web.servlets.SessionAttributes.ID_SESSION_PARAMETER;

@WebServlet(urlPatterns = "/menu/people/show-by-id")
public class PeopleShowByIdServlet extends HttpServlet {
    @Override
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException {
        showMenu(response);
    }

    @Override
    protected void doPost(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException {
        String id = MenuCommand.getValueOfParameter(ID_SESSION_PARAMETER, request, response);
        HttpSession session = request.getSession();
        session.setAttribute(ID_SESSION_PARAMETER, id);
        showPerson(id, response);
    }

    private void showMenu(HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        try (PrintWriter writer = response.getWriter()) {
            writer.write("<html><head><title>People Show By Id</title></head><body>");

            writer.write("<form action=\"show-by-id\" method=\"post\">");
            writer.write("<label for=\"id\">Id: </label>");
            writer.write("<input id=\"id\" type=\"text\" name=\"" + ID_SESSION_PARAMETER + "\">");
            writer.write("<input type=\"submit\" value=\"Show\">");
            writer.write("</form>");

            writer.write("</body></html>");
        }
    }

    private void showPerson(String id, HttpServletResponse response) throws IOException {
        PersonService personService = new PersonService();

        response.setContentType("text/html");
        try (PrintWriter writer = response.getWriter()) {
            writer.write("<html><head><title>People Show By Id</title></head><body>");
            Optional<Person> person = Optional.empty();

            try {
                if (id.isBlank()) {
                    throw new NotFoundException("Person not found");
                }
                person = personService.getPerson(Integer.parseInt(id));
            } catch (NotFoundException | IllegalArgumentException e) {
                Logger logger = new Logger();
                logger.error(getClass().getSimpleName(), e.getMessage(), e);
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
            }

            person.ifPresent(
                value -> writer.write(
                    "<h3>"
                        + value.firstName() + ": "
                        + value.lastName() + ", "
                        + value.contacts() + ", "
                        + value.gitHubNickname() + ", "
                        + value.role()
                        + "</h3>"
                ));

            writer.write("</body></html>");
        }
    }
}