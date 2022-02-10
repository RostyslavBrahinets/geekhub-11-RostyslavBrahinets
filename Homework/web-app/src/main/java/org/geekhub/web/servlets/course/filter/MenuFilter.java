package org.geekhub.web.servlets.course.filter;

import logger.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static org.geekhub.web.servlets.course.SessionAttributes.USER_NAME_SESSION_PARAMETER;

@WebFilter(urlPatterns = "/menu/*")
public class MenuFilter implements Filter {
    private static final String DELETE_METHOD = "DELETE";
    private static final String POST_METHOD = "POST";
    private static final String PUT_METHOD = "PUT";
    private static final List<String> ADMIN_PROTECTED_METHOD
        = List.of(DELETE_METHOD, POST_METHOD, PUT_METHOD);

//    private final Logger logger = new Logger();

    @Override
    public void doFilter(
        ServletRequest servletRequest,
        ServletResponse servletResponse,
        FilterChain chain
    ) throws IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestMethod = request.getMethod();

        if (ADMIN_PROTECTED_METHOD.contains(requestMethod)) {
            HttpSession session = request.getSession();
            String currentUserName = (String) session.getAttribute(USER_NAME_SESSION_PARAMETER);

            if (!Objects.equals(currentUserName, "admin")) {
                ((HttpServletResponse) servletResponse).sendError(
                    HttpServletResponse.SC_UNAUTHORIZED,
                    "Access denied. Required access rights: 'admin'"
                );
                return;
            }
        }

//        logger.info(getClass().getSimpleName(),
//            "Performing request to public aps in "
//                + getClass().getSimpleName() + " via " + requestMethod);
    }
}
