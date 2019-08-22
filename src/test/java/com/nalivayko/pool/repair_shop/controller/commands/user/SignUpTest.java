package com.nalivayko.pool.repair_shop.controller.commands.user;

import com.nalivayko.pool.repair_shop.controller.commands.Command;
import com.nalivayko.pool.repair_shop.services.UserService;
import com.nalivayko.pool.repair_shop.util.ParametersAndAttributes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SignUpTest {
    private static final String LOGIN = "some login";
    private static final String PASS = "some pass";
    private static final String LAST_NAME = "some lastName";
    private static final String FIRST_NAME = "some lastName";
    private static final String EMAIL = "some email";
    private static final String PHONE = "some phone";

    @Mock
    private HttpServletRequest request;
    @Mock
    private UserService userService;
    @Mock
    private HttpSession session;
    @Mock
    private HttpServletResponse response;
    @Mock
    private Command openHomePage;


    @Test
    public void shouldSignUpUser() throws ServletException, IOException {
//        when(request.getParameter(ParametersAndAttributes.LOGIN)).thenReturn(LOGIN);
//        when(request.getParameter(ParametersAndAttributes.PASS)).thenReturn(PASS);
//        when(request.getParameter(ParametersAndAttributes.LAST_NAME)).thenReturn(LAST_NAME);
//        when(request.getParameter(ParametersAndAttributes.FIRST_NAME)).thenReturn(FIRST_NAME);
//        when(request.getParameter(ParametersAndAttributes.EMAIL)).thenReturn(EMAIL);
//        when(request.getParameter(ParametersAndAttributes.PHONE)).thenReturn(PHONE);
//
//        when(request.getSession()).thenReturn(session);
//
//        new SignUp(userService, openHomePage).execute(request, response);
//
//        verify(userService).create(LOGIN, PASS, FIRST_NAME, LAST_NAME, EMAIL, PHONE);
    }
}
