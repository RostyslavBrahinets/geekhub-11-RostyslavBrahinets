package org.geekhub.web.servlets.menu.person;

import exceptions.ValidationException;
import logger.Logger;
import org.geekhub.web.servlets.menu.MenuCommand;
import services.PersonService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static org.geekhub.web.servlets.SessionAttributes.*;

@WebServlet(urlPatterns = "/menu/people/add")
public class PeopleAddServlet extends HttpServlet {
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
        String firstName = MenuCommand
            .getValueOfParameter(NAME_SESSION_PARAMETER, request, response);
        String lastName = MenuCommand
            .getValueOfParameter(SURNAME_SESSION_PARAMETER, request, response);
        String nickName = MenuCommand
            .getValueOfParameter(NICKNAME_SESSION_PARAMETER, request, response);
        String role = MenuCommand
            .getValueOfParameter(ROLE_SESSION_PARAMETER, request, response);
        HttpSession session = request.getSession();
        session.setAttribute(NAME_SESSION_PARAMETER, firstName);
        session.setAttribute(SURNAME_SESSION_PARAMETER, lastName);
        session.setAttribute(NICKNAME_SESSION_PARAMETER, nickName);
        session.setAttribute(ROLE_SESSION_PARAMETER, role);
        List<String> contacts = List.of();
        addPerson(firstName, lastName, contacts, nickName, role, response);
    }

    private void showMenu(HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        try (PrintWriter writer = response.getWriter()) {
            writer.write("<html><head><title>Courses Add</title></head><body>");

            writer.write("<form action=\"add\" method=\"post\">");
            writer.write("<label for=\"name\">First name: </label>");
            writer.write("<input id=\"name\" type=\"text\" name=\""
                + NAME_SESSION_PARAMETER + "\"></br>");
            writer.write("<label for=\"surname\">Last name: </label>");
            writer.write("<input id=\"surname\" type=\"text\" name=\""
                + SURNAME_SESSION_PARAMETER + "\"></br>");
            writer.write("<label for=\"nickname\">Nickname: </label>");
            writer.write("<input id=\"nickname\" type=\"text\" name=\""
                + NICKNAME_SESSION_PARAMETER + "\"></br>");
            writer.write("<label for=\"role\">Role (STUDENT, TEACHER): </label>");
            writer.write("<input id=\"role\" type=\"text\" name=\""
                + ROLE_SESSION_PARAMETER + "\"></br>");
            writer.write("<input type=\"submit\" value=\"Add\">");
            writer.write("</form>");

            writer.write("</body></html>");
        }
    }

    private void addPerson(
        String firstName,
        String lastName,
        List<String> contacts,
        String nickName,
        String role,
        HttpServletResponse response
    ) throws IOException {
        PersonService personService = new PersonService();

        response.setContentType("text/html");
        try (PrintWriter writer = response.getWriter()) {
            writer.write("<html><head><title>People Add</title></head><body>");
            try {
                personService.addPerson(firstName, lastName, contacts, nickName, role.toUpperCase());
            } catch (ValidationException | IllegalArgumentException e) {
                Logger logger = new Logger();
                logger.error(getClass().getSimpleName(), e.getMessage(), e);
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
            }
            writer.write("<h1>Person with name '" + firstName + "' added</h1>");
            writer.write("</body></html>");
        }
    }
}
