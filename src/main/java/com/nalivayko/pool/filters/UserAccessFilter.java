package com.nalivayko.pool.filters;

import com.nalivayko.pool.model.User;
import com.nalivayko.pool.model.enums.UserRole;
import com.nalivayko.pool.util.PagesPath;
import com.nalivayko.pool.util.UrlRequests;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserAccessFilter implements Filter {
    private static final String[] restrictedForUnregistered = {UrlRequests.MANAGER_PAGE,
            UrlRequests.MASTER_PAGE,
            UrlRequests.CUSTOMER};

    private String contextPath;

    @Override
    public void init(FilterConfig filterConfig) {
        contextPath = filterConfig.getServletContext().getContextPath();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);

        String requestPath = request.getRequestURI();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            for (String restrictedUrl : restrictedForUnregistered) {
                if (requestPath.contains(restrictedUrl)) {
                    response.sendRedirect(contextPath + PagesPath.LOGIN);
                    return;
                }
            }
        } else {
            if (!userPermittedToPerformRequest(user, requestPath)) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * @param user - current session user
     * @param url  - request url
     * @return true - if user permitted to perform request represented by url
     */
    private boolean userPermittedToPerformRequest(User user, String url) {
        UserRole userRole = user.getUserRole();
        if (url.contains(UrlRequests.MASTER_PAGE)) {
            return userRole == UserRole.MASTER;
        } else if (url.contains(UrlRequests.MANAGER_PAGE)) {
            return userRole == UserRole.MANAGER;
        } else if (url.contains(UrlRequests.CUSTOMER)) {
            return userRole == UserRole.MANAGER;
        } else {
            return true;
        }
    }

    @Override
    public void destroy() {
    }
}
