package org.geekhub.web.servlets.menu.person;

import models.Person;
import services.PersonService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static org.geekhub.web.servlets.SessionAttributes.COMMAND_SESSION_PARAMETER;

@WebServlet(urlPatterns = "/menu/people/show")
public class PeopleShowServlet extends HttpServlet {
    @Override
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException {
        showMenu(response);
    }

    private void showMenu(HttpServletResponse response) throws IOException {
        PersonService personService = new PersonService();
        List<Person> people = personService.getPeople();

        response.setContentType("text/html");
        try (PrintWriter writer = response.getWriter()) {
            writer.write("<html><head><title>People Show</title></head><body>");
            if (people.size() == 0) {
                showMenuIfPeopleNotFound(response);
                return;
            }
            showMenuIfPeopleFound(people, response);
            writer.write("</body></html>");
        }
    }

    private void showMenuIfPeopleNotFound(HttpServletResponse response) throws IOException {
        try (PrintWriter writer = response.getWriter()) {
            writer.write("<h1>People not found!<h1>");
            writer.write("<h1>Do you want add new person?<h1>");
            writer.write("<form action=\"add\" method=\"get\">");
            writer.write("<input type=\"submit\" name=\"" + COMMAND_SESSION_PARAMETER
                + "\" value=\"Add new\"></br></br>");
            writer.write("</form>");
        }
    }

    private void showMenuIfPeopleFound(
        List<Person> people,
        HttpServletResponse response
    ) throws IOException {
        try (PrintWriter writer = response.getWriter()) {
            writer.write("<h1>People:</h1><ul>");
            for (Person person : people) {
                writer.write("<li>"
                    + person.firstName() + ": "
                    + person.lastName() + ", "
                    + person.contacts() + ", "
                    + person.gitHubNickname() + ", "
                    + person.role()
                    + "</li>");
            }
            writer.write("<ul>");
        }
    }
}
