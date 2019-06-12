package com.nalivayko.pool.repair_shop.controller.commands.about;

import com.nalivayko.pool.repair_shop.controller.commands.pagination.AbstractPagination;
import com.nalivayko.pool.repair_shop.services.FeedbackService;
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
public class OpenAboutPageTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private FeedbackService feedbackService;
    @Mock
    private AbstractPagination<FeedbackService> pagination;
    @Mock
    private RequestDispatcher requestDispatcher;

    @Test
    public void shouldForwardToAboutPage() throws ServletException, IOException {
        when(request.getRequestDispatcher(PagesPath.ABOUT)).thenReturn(requestDispatcher);

        new OpenAboutPage(feedbackService, pagination).execute(request, response);

        verify(requestDispatcher).forward(request, response);
    }
}