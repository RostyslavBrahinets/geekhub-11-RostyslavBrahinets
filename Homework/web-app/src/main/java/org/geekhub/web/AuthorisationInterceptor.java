package org.geekhub.web;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthorisationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(
        HttpServletRequest request,
        HttpServletResponse response,
        Object handler
    ) throws Exception {
        String path = request.getRequestURI()
            .substring(request.getContextPath().length())
            .replaceAll("[/]+$", "");
        HttpSession session = request.getSession();

        boolean loggedIn = session != null
            && session.getAttribute(SessionAttributes.USER_NAME_SESSION_PARAMETER) != null;
        boolean allowedPath = path.equals("/authorisation");

        if (!(loggedIn || allowedPath)) {
            response.sendRedirect(request.getContextPath() + "/authorisation");
        }

        return true;
    }
}
