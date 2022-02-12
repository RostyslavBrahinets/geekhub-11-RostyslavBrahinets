package org.geekhub.web.servlets.menu.lection;

import models.Lection;
import services.LectionService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static org.geekhub.web.servlets.SessionAttributes.COMMAND_SESSION_PARAMETER;

@WebServlet(urlPatterns = "/menu/lections/show")
public class LectionsShowServlet extends HttpServlet {
    @Override
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException {
        showMenu(response);
    }

    private void showMenu(HttpServletResponse response) throws IOException {
        LectionService lectionService = new LectionService();
        List<Lection> lections = lectionService.getLections();

        response.setContentType("text/html");
        try (PrintWriter writer = response.getWriter()) {
            writer.write("<html><head><title>Lections Show</title></head><body>");
            if (lections.size() == 0) {
                showMenuIfLectionsNotFound(response);
                return;
            }
            showMenuIfLectionsFound(lections, response);
            writer.write("</body></html>");
        }
    }

    private void showMenuIfLectionsNotFound(HttpServletResponse response) throws IOException {
        try (PrintWriter writer = response.getWriter()) {
            writer.write("<h1>Lections not found!<h1>");
            writer.write("<h1>Do you want add new lections?<h1>");
            writer.write("<form action=\"add\" method=\"get\">");
            writer.write("<input type=\"submit\" name=\"" + COMMAND_SESSION_PARAMETER
                + "\" value=\"Add new\">");
            writer.write("</form>");
        }
    }

    private void showMenuIfLectionsFound(
        List<Lection> lections,
        HttpServletResponse response
    ) throws IOException {
        try (PrintWriter writer = response.getWriter()) {
            writer.write("<h1>Lections:</h1><ul>");
            for (Lection lection : lections) {
                writer.write("<li>"
                    + lection.name() + ": "
                    + lection.describe() + ", "
                    + lection.resources() + ", "
                    + lection.lecturer().firstName() + ", "
                    + lection.homeWorks() + ", ["
                    + lection.creationDate() + "]"
                    + "</li>");
            }
            writer.write("<ul>");
        }
    }
}
