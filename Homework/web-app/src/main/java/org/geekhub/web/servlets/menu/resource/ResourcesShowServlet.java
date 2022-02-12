package org.geekhub.web.servlets.menu.resource;

import models.Resource;
import services.ResourceService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static org.geekhub.web.servlets.SessionAttributes.COMMAND_SESSION_PARAMETER;

@WebServlet(urlPatterns = "/menu/resources/show")
public class ResourcesShowServlet extends HttpServlet {
    @Override
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException {
        showMenu(response);
    }

    private void showMenu(HttpServletResponse response) throws IOException {
        ResourceService resourceService = new ResourceService();
        List<Resource> resources = resourceService.getResources();

        response.setContentType("text/html");
        try (PrintWriter writer = response.getWriter()) {
            writer.write("<html><head><title>Resources Show</title></head><body>");
            if (resources.size() == 0) {
                showMenuIfCoursesNotFound(response);
                return;
            }
            showMenuIfCoursesFound(resources, response);
            writer.write("</body></html>");
        }
    }

    private void showMenuIfCoursesNotFound(HttpServletResponse response) throws IOException {
        try (PrintWriter writer = response.getWriter()) {
            writer.write("<h1>Resources not found!<h1>");
            writer.write("<h1>Do you want add new resource?<h1>");
            writer.write("<form action=\"add\" method=\"get\">");
            writer.write("<input type=\"submit\" name=\"" + COMMAND_SESSION_PARAMETER
                + "\" value=\"Add new\"></br></br>");
            writer.write("</form>");
        }
    }

    private void showMenuIfCoursesFound(
        List<Resource> resources,
        HttpServletResponse response
    ) throws IOException {
        try (PrintWriter writer = response.getWriter()) {
            writer.write("<h1>Resources:</h1><ul>");
            for (Resource resource : resources) {
                writer.write("<li>"
                    + resource.name() + ": "
                    + resource.type() + ", "
                    + resource.data()
                    + "</li>");
            }
            writer.write("<ul>");
        }
    }
}
