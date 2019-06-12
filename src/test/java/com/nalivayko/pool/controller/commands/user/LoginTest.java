package com.nalivayko.pool.controller.commands.user;

import com.nalivayko.pool.controller.commands.Command;
import com.nalivayko.pool.model.User;
import com.nalivayko.pool.services.UserService;
import com.nalivayko.pool.util.ParametersAndAttributes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoginTest {
    private static final String LOGIN = "some login";
    private static final String PASS = "some pass";

    @Mock
    private HttpServletRequest request;
    @Mock
    private UserService userService;
    @Mock
    private User user;
    @Mock
    private HttpSession session;
    @Mock
    private HttpServletResponse response;
    @Mock
    private Command openHomePage;
    @Mock
    private Command openLoginPage;

    @Test
    public void shouldLoginUserWithCorrectUsernameAndPass() throws ServletException, IOException {
        when(request.getParameter(ParametersAndAttributes.LOGIN)).thenReturn(LOGIN);
        when(request.getParameter(ParametersAndAttributes.PASSWORD)).thenReturn(PASS);
        when(request.getSession()).thenReturn(session);

        when(userService.validate(LOGIN, PASS)).thenReturn(user);

        new Login(userService, openHomePage, openLoginPage).execute(request, response);

        verify(session).setAttribute(ParametersAndAttributes.USER, user);
    }

    @Test
    public void shouldNotLoginUserWithWrongUsernameOrPass() throws ServletException, IOException {
        when(request.getParameter(ParametersAndAttributes.LOGIN)).thenReturn(LOGIN);
        when(request.getParameter(ParametersAndAttributes.PASSWORD)).thenReturn(PASS);
        when(request.getSession()).thenReturn(session);

        when(userService.validate(LOGIN, PASS)).thenReturn(null);

        new Login(userService, openHomePage, openLoginPage).execute(request, response);

        verify(session, never()).setAttribute(ParametersAndAttributes.USER, user);
    }
}
