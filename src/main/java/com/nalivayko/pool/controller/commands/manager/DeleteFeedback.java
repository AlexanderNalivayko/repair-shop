package com.nalivayko.pool.controller.commands.manager;

import com.nalivayko.pool.controller.commands.Command;
import com.nalivayko.pool.services.FeedbackService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteFeedback implements Command {

    private FeedbackService feedbackService;
    private Command openAboutPage;

    public DeleteFeedback(FeedbackService feedbackService, Command openFeedbackPage) {
        this.feedbackService = feedbackService;
        this.openAboutPage = openFeedbackPage;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int deleteId = Integer.parseInt(request.getParameter("delete-btn"));
        feedbackService.delete(deleteId);
        openAboutPage.execute(request, response);
    }
}
