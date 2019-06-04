package com.nalivayko.pool.filters;

import com.nalivayko.pool.controller.commands.Command;
import com.nalivayko.pool.controller.commands.user.OpenLoginPage;
import com.nalivayko.pool.model.User;
import com.nalivayko.pool.model.enums.UserRole;
import com.nalivayko.pool.util.UrlRequests;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * {@code UserAccessFilter} - checks if there is user in session and if user role
 * allowing to perform such request
 *
 * Redirect to login page - if null user trying to perform specific requests
 * Redirect to 403 if user not allowed to perform such request.
 */
public class UserAccessFilter implements Filter {
    private static final String[] restrictedForUnregistered = {UrlRequests.MANAGER_PAGE,
            UrlRequests.MASTER_PAGE,
            UrlRequests.CUSTOMER,
            UrlRequests.REPAIR_PAGE};

    private Command openLoginPage;

    @Override
    public void init(FilterConfig filterConfig) {
        this.openLoginPage = new OpenLoginPage();
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
                    openLoginPage.execute(request, response);
                    return;
                }
            }
        } else {
            if (!userPermittedToPerformRequest(user, requestPath)) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
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
