package com.nalivayko.pool.repair_shop.filters;

import com.nalivayko.pool.repair_shop.controller.commands.Command;
import com.nalivayko.pool.repair_shop.controller.commands.user.OpenLoginPage;
import com.nalivayko.pool.repair_shop.model.User;
import com.nalivayko.pool.repair_shop.model.enums.UserRole;
import com.nalivayko.pool.repair_shop.util.ParametersAndAttributes;
import com.nalivayko.pool.repair_shop.util.UrlRequests;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * {@code UserAccessFilter} - checks if there is user in session and if user role
 * allowing to perform such request
 * Redirects to login page - if null user trying to perform specific requests
 * Sends 403 if user not allowed to perform such request.
 */
public class UserAccessFilter implements Filter {
    private static final String[] RESTRICTED_FOR_UNREGISTERED = {
            UrlRequests.MANAGER_PAGE,
            UrlRequests.MASTER_PAGE,
            UrlRequests.CUSTOMER,
            UrlRequests.REPAIR_PAGE,
            UrlRequests.ABOUT_PAGE_LEAVE_FEEDBACK};
    private static final String[] RESTRICTED_FOR_CUSTOMER = {
            UrlRequests.LOGIN_PAGE,
            UrlRequests.MASTER_PAGE,
            UrlRequests.MANAGER_PAGE};
    private static final String[] RESTRICTED_FOR_MANAGER = {
            UrlRequests.REPAIR_PAGE,
            UrlRequests.MASTER_PAGE};
    private static final String[] RESTRICTED_FOR_MASTER = {
            UrlRequests.REPAIR_PAGE,
            UrlRequests.MANAGER_PAGE};

    private static Command openLoginPage = new OpenLoginPage();

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestPath = request.getRequestURI();
        User user = (User) request.getSession().getAttribute(ParametersAndAttributes.USER);

        if (user == null) {
            for (String restrictedUrl : RESTRICTED_FOR_UNREGISTERED) {
                if (requestPath.contains(restrictedUrl)) {
                    openLoginPage.execute(request, response);
                }
            }
        } else if (!userPermittedToPerformRequest(user, requestPath)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * Cheeks if url contains specific-user path and returns true if user
     * corresponding to this path.
     * If url doesn't contains specific-user path it checks if current path is
     * restricted for this type of user
     *
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
            return userRole == UserRole.CUSTOMER;
        }
        if (user.getUserRole() == UserRole.MASTER) {
            for (String restricted : RESTRICTED_FOR_MASTER) {
                if (url.contains(restricted)) {
                    return false;
                }
            }
        } else if (user.getUserRole() == UserRole.MANAGER) {
            for (String restricted : RESTRICTED_FOR_MANAGER) {
                if (url.contains(restricted)) {
                    return false;
                }
            }
        } else if (user.getUserRole() == UserRole.CUSTOMER) {
            for (String restricted : RESTRICTED_FOR_CUSTOMER) {
                if (url.contains(restricted)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void destroy() {
    }
}
