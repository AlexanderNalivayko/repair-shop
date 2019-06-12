package com.nalivayko.pool.repair_shop.controller.commands.user;

import com.nalivayko.pool.repair_shop.util.PagesPath;
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
public class OpenLoginPageTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private RequestDispatcher dispatcher;

    @Test
    public void shouldForwardToLoginPage() throws ServletException, IOException {
        when(request.getRequestDispatcher(PagesPath.LOGIN)).thenReturn(dispatcher);
        new OpenLoginPage().execute(request, response);
        verify(dispatcher).forward(request, response);
    }
}
