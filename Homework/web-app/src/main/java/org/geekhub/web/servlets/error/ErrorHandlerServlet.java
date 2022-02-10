package org.geekhub.web.servlets.error;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static javax.servlet.RequestDispatcher.ERROR_MESSAGE;
import static javax.servlet.RequestDispatcher.ERROR_STATUS_CODE;

@WebServlet(urlPatterns = "/error-handler")
public class ErrorHandlerServlet extends HttpServlet {
    @Override
    protected void service(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException {
        showError(request, response);
    }

    private void showError(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException {
        response.setContentType("text/html");

        try (PrintWriter writer = response.getWriter()) {
            writer.write("<html><head><title>Error</title></head><body>");
            writer.write("<h2>Error!</h2>");
            writer.write("<li>Reason : " + request.getAttribute(ERROR_MESSAGE) + "</li>");
            writer.write("<li>Status code: " + request.getAttribute(ERROR_STATUS_CODE) + "</li>");
            writer.write("</body></html>");
            writer.flush();
        }
    }
}
