package com.nalivayko.pool.filters;

import com.nalivayko.pool.model.User;
import com.nalivayko.pool.model.enums.UserRole;
import com.nalivayko.pool.util.ParametersAndAttributes;
import com.nalivayko.pool.util.UrlRequests;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserAccessFilterTest {

    private UserAccessFilter userAccessFilter = new UserAccessFilter();

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private FilterChain filterChain;
    @Mock
    private HttpSession session;
    @Mock
    private User user;

    @Test
    public void shouldForbidUserToAccessManagerPage() throws IOException, ServletException {
        when(request.getRequestURI()).thenReturn(UrlRequests.MANAGER_PAGE);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(ParametersAndAttributes.USER)).thenReturn(user);
        when(user.getUserRole()).thenReturn(UserRole.CUSTOMER);

        userAccessFilter.doFilter(request, response, filterChain);

        verify(response).sendError(HttpServletResponse.SC_FORBIDDEN);
    }

    @Test
    public void shouldForbidUserToAccessMasterPage() throws IOException, ServletException {
        when(request.getRequestURI()).thenReturn(UrlRequests.MASTER_PAGE);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(ParametersAndAttributes.USER)).thenReturn(user);
        when(user.getUserRole()).thenReturn(UserRole.CUSTOMER);

        userAccessFilter.doFilter(request, response, filterChain);

        verify(response).sendError(HttpServletResponse.SC_FORBIDDEN);
    }

    @Test
    public void shouldForbidUserToAccessLoginPage() throws IOException, ServletException {
        when(request.getRequestURI()).thenReturn(UrlRequests.LOGIN_PAGE);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(ParametersAndAttributes.USER)).thenReturn(user);
        when(user.getUserRole()).thenReturn(UserRole.CUSTOMER);

        userAccessFilter.doFilter(request, response, filterChain);

        verify(response).sendError(HttpServletResponse.SC_FORBIDDEN);
    }

    @Test
    public void shouldForbidMasterToAccessManagerPage() throws IOException, ServletException {
        when(request.getRequestURI()).thenReturn(UrlRequests.MANAGER_PAGE);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(ParametersAndAttributes.USER)).thenReturn(user);
        when(user.getUserRole()).thenReturn(UserRole.MASTER);

        userAccessFilter.doFilter(request, response, filterChain);

        verify(response).sendError(HttpServletResponse.SC_FORBIDDEN);
    }

    @Test
    public void shouldForbidMasterToAccessRepairPage() throws IOException, ServletException {
        when(request.getRequestURI()).thenReturn(UrlRequests.REPAIR_PAGE);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(ParametersAndAttributes.USER)).thenReturn(user);
        when(user.getUserRole()).thenReturn(UserRole.MASTER);

        userAccessFilter.doFilter(request, response, filterChain);

        verify(response).sendError(HttpServletResponse.SC_FORBIDDEN);
    }

    @Test
    public void shouldForbidManagerToAccessRepairPage() throws IOException, ServletException {
        when(request.getRequestURI()).thenReturn(UrlRequests.REPAIR_PAGE);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(ParametersAndAttributes.USER)).thenReturn(user);
        when(user.getUserRole()).thenReturn(UserRole.MANAGER);

        userAccessFilter.doFilter(request, response, filterChain);

        verify(response).sendError(HttpServletResponse.SC_FORBIDDEN);
    }

    @Test
    public void shouldForbidManagerToAccessMaster() throws IOException, ServletException {
        when(request.getRequestURI()).thenReturn(UrlRequests.MASTER_PAGE);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(ParametersAndAttributes.USER)).thenReturn(user);
        when(user.getUserRole()).thenReturn(UserRole.MANAGER);

        userAccessFilter.doFilter(request, response, filterChain);

        verify(response).sendError(HttpServletResponse.SC_FORBIDDEN);
    }
}