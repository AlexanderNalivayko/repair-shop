package com.nalivayko.pool.controller.commands;

import com.nalivayko.pool.util.PagesPath;
import com.nalivayko.pool.util.UrlRequests;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OpenHomePageTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private RequestDispatcher dispatcher;

    @Test
    public void shouldForwardToHomePage() throws ServletException, IOException {
        when(request.getRequestDispatcher(PagesPath.HOME)).thenReturn(dispatcher);
        when(request.getContextPath()).thenReturn("/");
        when(request.getRequestURI()).thenReturn("/" + UrlRequests.SITE + UrlRequests.HOME_PAGE);

        new OpenHomePage().execute(request, response);

        verify(dispatcher).forward(request, response);
    }
}