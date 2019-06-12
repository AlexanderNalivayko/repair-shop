package com.nalivayko.pool.repair_shop.controller.commands.about;

import com.nalivayko.pool.repair_shop.model.User;
import com.nalivayko.pool.repair_shop.services.FeedbackService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LeaveFeedbackTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private FeedbackService feedbackService;
    @Mock
    private OpenAboutPage openAboutPage;
    @Mock
    private HttpSession session;
    @Mock
    private User user;


    @Test
    public void shouldCreateFeedbackAndForward() throws ServletException, IOException {
        String message = "msg";

        when(request.getParameter(any())).thenReturn(message);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(any())).thenReturn(user);

        new LeaveFeedback(feedbackService, openAboutPage).execute(request, response);

        verify(feedbackService).create(user, message);
        verify(openAboutPage).execute(request, response);
    }

    @Test
    public void shouldNotAddEmptyFeedback() throws ServletException, IOException {
        String message = "";

        when(request.getParameter(any())).thenReturn(message);
        when(request.getSession()).thenReturn(session);

        new LeaveFeedback(feedbackService, openAboutPage).execute(request, response);
        verify(feedbackService, never()).create(any(), any());
    }

    @Test
    public void shouldNotAddNullComment() throws ServletException, IOException {
        String message = null;

        when(request.getParameter(any())).thenReturn(message);
        when(request.getSession()).thenReturn(session);

        new LeaveFeedback(feedbackService, openAboutPage).execute(request, response);
        verify(feedbackService, never()).create(any(), any());
    }

}