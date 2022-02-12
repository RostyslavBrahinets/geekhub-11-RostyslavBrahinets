package org.geekhub.web.servlets.menu.course;

import models.Course;
import services.CourseService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static org.geekhub.web.servlets.SessionAttributes.COMMAND_SESSION_PARAMETER;

@WebServlet(urlPatterns = "/menu/courses/show")
public class CoursesShowServlet extends HttpServlet {
    @Override
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException {
        showMenu(response);
    }

    private void showMenu(HttpServletResponse response) throws IOException {
        CourseService courseService = new CourseService();
        List<Course> courses = courseService.getCourses();

        response.setContentType("text/html");
        try (PrintWriter writer = response.getWriter()) {
            writer.write("<html><head><title>Courses Show</title></head><body>");
            if (courses.size() == 0) {
                showMenuIfCoursesNotFound(response);
                return;
            }
            showMenuIfCoursesFound(courses, response);
            writer.write("</body></html>");
        }
    }

    private void showMenuIfCoursesNotFound(HttpServletResponse response) throws IOException {
        try (PrintWriter writer = response.getWriter()) {
            writer.write("<h1>Courses not found!<h1>");
            writer.write("<h1>Do you want add new course?<h1>");
            writer.write("<form action=\"add\" method=\"get\">");
            writer.write("<input type=\"submit\" name=\"" + COMMAND_SESSION_PARAMETER
                + "\" value=\"Add new\"></br></br>");
            writer.write("</form>");
        }
    }

    private void showMenuIfCoursesFound(
        List<Course> courses,
        HttpServletResponse response
    ) throws IOException {
        try (PrintWriter writer = response.getWriter()) {
            writer.write("<h1>Courses:</h1><ul>");
            for (Course course : courses) {
                writer.write("<li>"
                    + course.name() + ": "
                    + course.lections() + ", "
                    + course.students()
                    + "</li>");
            }
            writer.write("<ul>");
        }
    }
}
