package com.nalivayko.pool.controller.commands.user;

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
import java.io.IOException;
import java.io.PrintWriter;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ValidateUsernameTest {
    private static final String USERNAME = "someUserName";
    @Mock
    private HttpServletRequest request;
    @Mock
    private UserService userService;
    @Mock
    private HttpServletResponse response;
    @Mock
    private PrintWriter writer;
    @Mock
    private User user;


    @Test
    public void shouldPrintFreeIfUserIsFree() throws IOException, ServletException {
        when(response.getWriter()).thenReturn(writer);
        when(request.getParameter(ParametersAndAttributes.LOGIN)).thenReturn(anyString());
        when(userService.getUserByUsername(USERNAME)).thenReturn(user);

        new ValidateUsername(userService).execute(request, response);

        verify(writer).print(ParametersAndAttributes.TAKEN);
    }

    @Test
    public void shouldPrintTakenIfUserIsTaken() throws ServletException, IOException {
        when(response.getWriter()).thenReturn(writer);
        when(request.getParameter(ParametersAndAttributes.LOGIN)).thenReturn(anyString());
        when(userService.getUserByUsername(USERNAME)).thenReturn(null);

        new ValidateUsername(userService).execute(request, response);

        verify(writer).print(ParametersAndAttributes.FREE);
    }
}