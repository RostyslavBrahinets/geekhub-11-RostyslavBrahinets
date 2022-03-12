package org.geekhub.web.servlets.menu.person;

import config.AppConfig;
import logger.Logger;
import org.geekhub.web.servlets.menu.MenuCommand;
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

import static org.geekhub.web.servlets.SessionAttributes.*;

@WebServlet(urlPatterns = "/menu/people/add")
public class PersonAddServlet extends HttpServlet {
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
            .getValueOfParameter(NAME_SESSION_PARAMETER, request);
        String lastName = MenuCommand
            .getValueOfParameter(SURNAME_SESSION_PARAMETER, request);
        String nickName = MenuCommand
            .getValueOfParameter(NICKNAME_SESSION_PARAMETER, request);
        String role = MenuCommand
            .getValueOfParameter(ROLE_SESSION_PARAMETER, request);
        String courseId = MenuCommand
            .getValueOfParameter(ID_SESSION_PARAMETER, request);
        HttpSession session = request.getSession();
        session.setAttribute(NAME_SESSION_PARAMETER, firstName);
        session.setAttribute(SURNAME_SESSION_PARAMETER, lastName);
        session.setAttribute(NICKNAME_SESSION_PARAMETER, nickName);
        session.setAttribute(ROLE_SESSION_PARAMETER, role);
        try {
            addPerson(firstName, lastName, nickName, role, Integer.parseInt(courseId), response);
        } catch (Exception e) {
            Logger logger = new Logger();
            logger.error(getClass().getSimpleName(), e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    private void showMenu(HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        try (PrintWriter writer = response.getWriter()) {
            writer.write("<html><head><title>Person Add</title></head><body>");

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
            writer.write("<label for=\"courseId\">Course id: </label>");
            writer.write("<input id=\"courseId\" type=\"text\" name=\""
                + ID_SESSION_PARAMETER + "\"></br>");
            writer.write("<input type=\"submit\" value=\"Add\">");
            writer.write("</form>");

            writer.write("</body></html>");
        }
    }

    private void addPerson(
        String firstName,
        String lastName,
        String nickName,
        String role,
        int courseId,
        HttpServletResponse response
    ) throws IOException, SQLException {
        AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(AppConfig.class);
        PersonService personService =
            applicationContext.getBean(PersonService.class);

        response.setContentType("text/html");
        try (PrintWriter writer = response.getWriter()) {
            writer.write("<html><head><title>Person Add</title></head><body>");
            personService.addPerson(
                firstName, lastName, nickName, role.toUpperCase(), courseId
            );
            writer.write("<h1>Person with name '" + firstName + "' added</h1>");
            writer.write("</body></html>");
        }
    }
}
