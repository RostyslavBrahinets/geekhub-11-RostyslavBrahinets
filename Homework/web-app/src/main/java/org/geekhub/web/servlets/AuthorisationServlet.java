package org.geekhub.web.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@WebServlet(urlPatterns = "/auth")
public class AuthorisationServlet extends HttpServlet {
    @Override
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException {
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

    @Override
    protected void doPost(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException {
        Optional<String> optionalUserName;

        try {
            optionalUserName = extractUserNameParameter(request);
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
            return;
        }

        String userName = optionalUserName.orElse("");

        HttpSession session = request.getSession();
        session.setAttribute(SessionAttributes.USER_NAME_SESSION_PARAMETER, userName);

        List<String> users = List.of("admin", "user");

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
        session.setAttribute(SessionAttributes.USER_NAME_SESSION_PARAMETER, null);
    }

    private Optional<String> extractUserNameParameter(HttpServletRequest request) {
        String userName = request.getParameter(SessionAttributes.USER_NAME_SESSION_PARAMETER);
        return Optional.ofNullable(userName);
    }
}
