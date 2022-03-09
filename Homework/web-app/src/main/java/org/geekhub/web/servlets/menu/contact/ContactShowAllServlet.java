package org.geekhub.web.servlets.menu.contact;

import config.AppConfig;
import logger.Logger;
import models.Contact;
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
import java.util.List;

import static org.geekhub.web.servlets.SessionAttributes.COMMAND_SESSION_PARAMETER;
import static org.geekhub.web.servlets.SessionAttributes.USER_NAME_SESSION_PARAMETER;

@WebServlet(urlPatterns = "/menu/contacts/show")
public class ContactShowAllServlet extends HttpServlet {
    @Override
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException {
        try {
            showMenu(request, response);
        } catch (SQLException e) {
            Logger logger = new Logger();
            logger.error(getClass().getSimpleName(), e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    private void showMenu(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException, SQLException {
        AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(AppConfig.class);
        ContactService contactService =
            applicationContext.getBean(ContactService.class);

        List<Contact> contacts = contactService.getContacts();

        response.setContentType("text/html");
        try (PrintWriter writer = response.getWriter()) {
            writer.write("<html><head><title>Contacts Show</title></head><body>");
            if (contacts.size() == 0) {
                showMenuIfContactsNotFound(request, response);
                return;
            }
            showMenuIfContactsFound(contacts, response);
            writer.write("</body></html>");
        }
    }

    private void showMenuIfContactsNotFound(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException {
        try (PrintWriter writer = response.getWriter()) {
            writer.write("<h1>Contacts not found!<h1>");

            HttpSession session = request.getSession();
            String userName = (String) session.getAttribute(USER_NAME_SESSION_PARAMETER);

            if (userName.equals("admin")) {
                writer.write("<h1>Do you want add new contact?<h1>");
                writer.write("<form action=\"add\" method=\"get\">");
                writer.write("<input type=\"submit\" name=\"" + COMMAND_SESSION_PARAMETER
                    + "\" value=\"Add new\"></br></br>");
                writer.write("</form>");
            }
        }
    }

    private void showMenuIfContactsFound(
        List<Contact> contacts,
        HttpServletResponse response
    ) throws IOException {
        try (PrintWriter writer = response.getWriter()) {
            writer.write("<h1>Contacts:</h1><ul>");
            for (Contact contact : contacts) {
                writer.write("<li>"
                    + contact.getEmail() + ": "
                    + contact.getPhone() + ", "
                    + "</li>");
            }
            writer.write("<ul>");
        }
    }
}
