package org.geekhub.web.servlets.menu.course;

import exceptions.ValidationException;
import models.Lection;
import models.Person;
import org.geekhub.web.servlets.menu.MenuCommand;
import services.CourseService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static org.geekhub.web.servlets.SessionAttributes.NAME_SESSION_PARAMETER;

@WebServlet(urlPatterns = "/menu/courses/add")
public class CoursesAddServlet extends HttpServlet {
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

        List<Lection> lectionsOfCourse = List.of();
        List<Person> studentsOfCourse = List.of();
        HttpSession session = request.getSession();
        session.setAttribute(NAME_SESSION_PARAMETER, name);
        addCourse(name, lectionsOfCourse, studentsOfCourse, response);
    }

    private void showMenu(HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        try (PrintWriter writer = response.getWriter()) {
            writer.write("<html><head><title>Courses Add</title></head><body>");

            writer.write("<form action=\"add\" method=\"post\">");
            writer.write("<label for=\"name\">Name: </label>");
            writer.write("<input id=\"name\" type=\"text\" name=\"" + NAME_SESSION_PARAMETER + "\">");
            writer.write("<input type=\"submit\" value=\"Add\">");
            writer.write("</form>");

            writer.write("</body></html>");
        }
    }

    private void addCourse(
        String name,
        List<Lection> lectionsOfCourse,
        List<Person> studentsOfCourse,
        HttpServletResponse response
    ) throws IOException {
        CourseService courseService = new CourseService();

        response.setContentType("text/html");
        try (PrintWriter writer = response.getWriter()) {
            writer.write("<html><head><title>Courses Add</title></head><body>");
            try {
                courseService.addCourse(name, lectionsOfCourse, studentsOfCourse);
            } catch (ValidationException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
            }
            writer.write("<h1>Course with name '" + name + "' added</h1>");
            writer.write("</body></html>");
        }
    }
}
