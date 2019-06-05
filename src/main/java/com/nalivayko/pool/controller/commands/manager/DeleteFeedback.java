package com.nalivayko.pool.controller.commands.manager;

import com.nalivayko.pool.controller.commands.Command;
import com.nalivayko.pool.services.FeedbackService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @see Command
 */
public class DeleteFeedback implements Command {
    private static final String DELETE_BTN = "delete-btn";
    private FeedbackService feedbackService;
    private Command openAboutPage;

    public DeleteFeedback(FeedbackService feedbackService, Command openFeedbackPage) {
        this.feedbackService = feedbackService;
        this.openAboutPage = openFeedbackPage;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int deleteId = Integer.parseInt(request.getParameter(DELETE_BTN));
        feedbackService.delete(deleteId);
        openAboutPage.execute(request, response);
    }
}
