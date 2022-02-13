package org.geekhub.web.servlets.menu.lection;

import exceptions.ValidationException;
import logger.Logger;
import models.HomeWork;
import models.Person;
import models.Resource;
import models.Role;
import org.geekhub.web.servlets.menu.MenuCommand;
import services.LectionService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static org.geekhub.web.servlets.SessionAttributes.*;

@WebServlet(urlPatterns = "/menu/lections/add")
public class LectionsAddServlet extends HttpServlet {
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
        String name = MenuCommand.getValueOfParameter(NAME_SESSION_PARAMETER, request, response);
        String describe = MenuCommand.getValueOfParameter(DESCRIBE_SESSION_PARAMETER, request, response);
        String lecturer = MenuCommand.getValueOfParameter(LECTURER_NAME_SESSION_PARAMETER, request, response);
        HttpSession session = request.getSession();
        session.setAttribute(NAME_SESSION_PARAMETER, name);
        session.setAttribute(DESCRIBE_SESSION_PARAMETER, describe);
        session.setAttribute(LECTURER_NAME_SESSION_PARAMETER, lecturer);
        List<Resource> resources = List.of();
        List<HomeWork> homeWorks = List.of();
        addLection(name, describe, resources, lecturer, homeWorks, response);
    }

    private void showMenu(HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        try (PrintWriter writer = response.getWriter()) {
            writer.write("<html><head><title>Lections Add</title></head><body>");

            writer.write("<form action=\"add\" method=\"post\">");
            writer.write("<label for=\"name\">Name of lection: </label>");
            writer.write("<input id=\"name\" type=\"text\" name=\""
                + NAME_SESSION_PARAMETER + "\"></br>");
            writer.write("<label for=\"describe\">Describe: </label>");
            writer.write("<input id=\"describe\" type=\"text\" name=\""
                + DESCRIBE_SESSION_PARAMETER + "\"></br>");
            writer.write("<label for=\"lecturerName\">Name of lecturer: </label>");
            writer.write("<input id=\"lecturerName\" type=\"text\" name=\""
                + LECTURER_NAME_SESSION_PARAMETER + "\"></br>");
            writer.write("<input type=\"submit\" value=\"Add\">");
            writer.write("</form>");

            writer.write("</body></html>");
        }
    }

    private void addLection(
        String name,
        String describe,
        List<Resource> resources,
        String lecturer,
        List<HomeWork> homeWorks,
        HttpServletResponse response
    ) throws IOException {
        LectionService lectionService = new LectionService();

        response.setContentType("text/html");
        try (PrintWriter writer = response.getWriter()) {
            writer.write("<html><head><title>Lections Add</title></head><body>");
            try {
                lectionService.addLection(
                    name, describe, resources,
                    new Person(lecturer, "LastName", List.of(), "Nickname", Role.TEACHER),
                    homeWorks);
            } catch (ValidationException | IllegalArgumentException e) {
                Logger logger = new Logger();
                logger.error(getClass().getSimpleName(), e.getMessage(), e);
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
            }
            writer.write("<h1>Lection with name '" + name + "' added</h1>");
            writer.write("</body></html>");
        }
    }
}