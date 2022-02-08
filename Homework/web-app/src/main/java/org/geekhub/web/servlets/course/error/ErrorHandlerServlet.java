package org.geekhub.web.servlets.course.error;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import static javax.servlet.RequestDispatcher.*;

@WebServlet(urlPatterns = "/error-handler")
public class ErrorHandlerServlet extends HttpServlet {
    @Override
    protected void doGet(
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
            Arrays.asList(
                    ERROR_STATUS_CODE,
                    ERROR_EXCEPTION_TYPE,
                    ERROR_MESSAGE)
                .forEach(e ->
                    writer.write("<strong>" + e + "</strong>: "
                        + request.getAttribute(e) + "</br>")
                );
            writer.write("</body></html>");
        }
    }
}
