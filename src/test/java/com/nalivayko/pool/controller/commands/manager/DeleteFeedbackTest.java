package com.nalivayko.pool.controller.commands.manager;

import com.nalivayko.pool.controller.commands.Command;
import com.nalivayko.pool.services.FeedbackService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DeleteFeedbackTest {
    private static final String DELETE_BTN = "delete-btn";

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private FeedbackService feedbackService;
    @Mock
    private Command openAboutPage;

    @Test
    public void shouldDeleteFeedback() throws ServletException, IOException {
        String stringFeedbackId = "1";

        when(request.getParameter(DELETE_BTN)).thenReturn(stringFeedbackId);

        new DeleteFeedback(feedbackService, openAboutPage).execute(request, response);

        verify(feedbackService).delete(Integer.parseInt(stringFeedbackId));
        verify(openAboutPage).execute(request, response);
    }
}