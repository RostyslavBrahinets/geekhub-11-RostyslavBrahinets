package org.geekhub.web.servlets.menu.course;

import exceptions.NotFoundException;
import models.Course;
import services.CourseService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import static org.geekhub.web.servlets.SessionAttributes.ID_SESSION_PARAMETER;

@WebServlet(urlPatterns = "/menu/courses/show-by-id")
public class CoursesShowByIdServlet extends HttpServlet {
    @Override
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException {
        response.setContentType("text/html");


        try (PrintWriter writer = response.getWriter()) {
            writer.write("<html><head><title>Courses Show</title></head><body>");

            writer.write("<form action=\"show-by-id\" method=\"post\">");
            writer.write("<label for=\"id\">Id: </label>");
            writer.write("<input id=\"id\" type=\"text\" name=\"" + ID_SESSION_PARAMETER + "\">");
            writer.write("<input type=\"submit\" value=\"Show\">");
            writer.write("</form>");

            writer.write("</body></html>");
        }
    }

    @Override
    protected void doPost(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException {
        Optional<String> optionalId;

        try {
            optionalId = extractIdParameter(request);
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
            return;
        }

        String id = optionalId.orElse("");

        HttpSession session = request.getSession();
        session.setAttribute(ID_SESSION_PARAMETER, id);

        CourseService courseService = new CourseService();

        response.setContentType("text/html");

        try (PrintWriter writer = response.getWriter()) {
            writer.write("<html><head><title>Courses Show By Id</title></head><body>");

            Optional<Course> course = courseService.getCourse(Integer.parseInt(id));
            course.ifPresent(
                value -> writer.write(
                    "<h3>"
                        + value.name() + ": "
                        + value.lections() + ", "
                        + value.students()
                        + "</h3>"
                ));

            writer.write("</body></html>");
        } catch (NotFoundException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    private Optional<String> extractIdParameter(HttpServletRequest request) {
        String id = request.getParameter(ID_SESSION_PARAMETER);
        return Optional.ofNullable(id);
    }
}
