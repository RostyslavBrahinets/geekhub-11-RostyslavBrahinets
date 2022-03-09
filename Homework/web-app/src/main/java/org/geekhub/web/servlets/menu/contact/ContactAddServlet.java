package org.geekhub.web.servlets.menu.contact;

import config.AppConfig;
import exceptions.ValidationException;
import logger.Logger;
import org.geekhub.web.servlets.menu.MenuCommand;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import services.ContactService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import static org.geekhub.web.servlets.SessionAttributes.*;

@WebServlet(urlPatterns = "/menu/contacts/add")
public class ContactAddServlet extends HttpServlet {
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
        String email = MenuCommand.getValueOfParameter(EMAIL_SESSION_PARAMETER, request, response);
        String phone = MenuCommand.getValueOfParameter(PHONE_SESSION_PARAMETER, request, response);
        String personId = MenuCommand
            .getValueOfParameter(ID_SESSION_PARAMETER, request, response);
        HttpSession session = request.getSession();
        session.setAttribute(EMAIL_SESSION_PARAMETER, email);
        session.setAttribute(PHONE_SESSION_PARAMETER, phone);
        session.setAttribute(ID_SESSION_PARAMETER, personId);
        addContact(email, phone, Integer.parseInt(personId), response);
    }

    private void showMenu(HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        try (PrintWriter writer = response.getWriter()) {
            writer.write("<html><head><title>Contact Add</title></head><body>");

            writer.write("<form action=\"add\" method=\"post\">");
            writer.write("<label for=\"email\">Email: </label>");
            writer.write("<input id=\"email\" type=\"text\" name=\""
                + EMAIL_SESSION_PARAMETER + "\"></br>");
            writer.write("<label for=\"phone\">Phone: </label>");
            writer.write("<input id=\"phone\" type=\"text\" name=\""
                + PHONE_SESSION_PARAMETER + "\"></br>");
            writer.write("<label for=\"personId\">Person id: </label>");
            writer.write("<input id=\"personId\" type=\"text\" name=\""
                + ID_SESSION_PARAMETER + "\"></br>");
            writer.write("<input type=\"submit\" value=\"Add\">");
            writer.write("</form>");

            writer.write("</body></html>");
        }
    }

    private void addContact(
        String email,
        String phone,
        int personId,
        HttpServletResponse response
    ) throws IOException {
        AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(AppConfig.class);
        ContactService contactService =
            applicationContext.getBean(ContactService.class);

        response.setContentType("text/html");
        try (PrintWriter writer = response.getWriter()) {
            writer.write("<html><head><title>Contact Add</title></head><body>");
            try {
                contactService.addContact(email, phone, personId);
            } catch (ValidationException | IllegalArgumentException | SQLException e) {
                Logger logger = new Logger();
                logger.error(getClass().getSimpleName(), e.getMessage(), e);
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
            }
            writer.write("<h1>Contact with name '" + email + "' added</h1>");
            writer.write("</body></html>");
        }
    }
}
