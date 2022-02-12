package org.geekhub.web.servlets.menu.lection;

import exceptions.NotFoundException;
import models.Lection;
import org.geekhub.web.servlets.menu.MenuCommand;
import services.LectionService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import static org.geekhub.web.servlets.SessionAttributes.ID_SESSION_PARAMETER;

@WebServlet(urlPatterns = "/menu/lections/show-by-id")
public class LectionsShowByIdServlet extends HttpServlet {
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
        showLection(id, response);
    }

    private void showMenu(HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        try (PrintWriter writer = response.getWriter()) {
            writer.write("<html><head><title>Lections Show By Id</title></head><body>");

            writer.write("<form action=\"show-by-id\" method=\"post\">");
            writer.write("<label for=\"id\">Id: </label>");
            writer.write("<input id=\"id\" type=\"text\" name=\"" + ID_SESSION_PARAMETER + "\">");
            writer.write("<input type=\"submit\" value=\"Show\">");
            writer.write("</form>");

            writer.write("</body></html>");
        }
    }

    private void showLection(String id, HttpServletResponse response) throws IOException {
        LectionService lectionService = new LectionService();

        response.setContentType("text/html");
        try (PrintWriter writer = response.getWriter()) {
            writer.write("<html><head><title>Lections Show By Id</title></head><body>");
            Optional<Lection> lection = Optional.empty();

            try {
                if (id.isBlank()) {
                    throw new NotFoundException("Lection not found");
                }
                lection = lectionService.getLection(Integer.parseInt(id));
            } catch (NotFoundException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
            }

            lection.ifPresent(
                value -> writer.write(
                    "<h3>"
                        + value.name() + ": "
                        + value.describe() + ", "
                        + value.resources() + ", "
                        + value.lecturer().firstName() + ", "
                        + value.homeWorks() + ", "
                        + value.creationDate() + ", "
                        + "</h3>"
                ));

            writer.write("</body></html>");
        }
    }
}
