package org.geekhub.web.servlets.menu.course;

import exceptions.NotFoundException;
import org.geekhub.web.servlets.menu.MenuCommand;
import services.CourseService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import static org.geekhub.web.servlets.SessionAttributes.ID_SESSION_PARAMETER;

@WebServlet(urlPatterns = "/menu/courses/delete")
public class CoursesDeleteServlet extends HttpServlet {
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
        deleteCourse(id, response);
    }

    private void showMenu(HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        try (PrintWriter writer = response.getWriter()) {
            writer.write("<html><head><title>Courses Delete</title></head><body>");

            writer.write("<form action=\"delete\" method=\"post\">");
            writer.write("<label for=\"id\">Id: </label>");
            writer.write("<input id=\"id\" type=\"text\" name=\"" + ID_SESSION_PARAMETER + "\">");
            writer.write("<input type=\"submit\" value=\"Delete\">");
            writer.write("</form>");

            writer.write("</body></html>");
        }
    }

    private void deleteCourse(String id, HttpServletResponse response) throws IOException {
        CourseService courseService = new CourseService();

        response.setContentType("text/html");
        try (PrintWriter writer = response.getWriter()) {
            writer.write("<html><head><title>Courses Delete</title></head><body>");
            try {
                if (id.isBlank()) {
                    throw new NotFoundException("Course not found");
                }
                courseService.deleteCourse(Integer.parseInt(id));
            } catch (NotFoundException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
            }
            writer.write("<h1>Course with id '" + id + "' deleted</h1>");
            writer.write("</body></html>");
        }
    }
}
