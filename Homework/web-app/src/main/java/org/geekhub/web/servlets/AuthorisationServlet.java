package org.geekhub.web.servlets;

import logger.Logger;
import db.DataBaseConnector;
import db.DataBaseStarter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.geekhub.web.servlets.SessionAttributes.USER_NAME_SESSION_PARAMETER;

@WebServlet(urlPatterns = "/auth")
public class AuthorisationServlet extends HttpServlet {
    @Override
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException {
        setDataBase();
        showMenu(response);
    }

    @Override
    protected void doPost(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException {
        RequestParameter parameter = new RequestParameter();
        parameter.setRequestParameterToSessionAttribute(
            USER_NAME_SESSION_PARAMETER,
            request, response
        );

        List<String> users = List.of("admin", "user");

        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute(USER_NAME_SESSION_PARAMETER);

        response.setContentType("text/html");
        if (users.contains(userName)) {
            response.sendRedirect("/main");
        } else {
            try (var writer = response.getWriter()) {
                writer.write("<html><head><title>Authorisation</title></head><body>");
                writer.write("<h1>Invalid name '" + userName
                    + "'! Input 'admin' or 'user'</h1></h1>");
                doGet(request, response);
                writer.write("</body></html>");
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.setAttribute(USER_NAME_SESSION_PARAMETER, null);
    }

    private void showMenu(HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        try (PrintWriter writer = response.getWriter()) {
            writer.write("<html><head><title>Authorisation</title></head><body>");
            writer.write("<h1>Login Page</h1>");
            writer.write("<form action=\"auth\" method=\"post\">");
            writer.write("<label for=\"name\">User login: </label>");
            writer.write("<input id=\"name\" type=\"text\" name=\"userName\" value=\"admin\">");
            writer.write("<input type=\"submit\" value=\"Login\">");
            writer.write("</form>");
            writer.write("</body></html>");
        }
    }

    private void setDataBase() {
        Logger logger = new Logger();

        try (
                Connection connection = DataBaseConnector.getConnection();
                Statement statement = connection.createStatement()
        ) {
            DataBaseStarter repository = new DataBaseStarter();
            repository.createTablesInDataBase();

            String sql = "select * from course";
            ResultSet resultSet = statement.executeQuery(sql);

            if (!resultSet.next()) {
                repository.insertDataToTablesInDataBase();
                logger.info(getClass().getName(),
                    "Tables created in database! Data inserted to tables!");
            } else {
                logger.info(getClass().getName(),
                    "Tables already created in database!");
            }
        } catch (SQLException | IOException e) {
            logger.error(getClass().getName(), e.getMessage(), e);
        }
    }
}
