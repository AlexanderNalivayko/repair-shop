package com.nalivayko.pool.repair_shop.controller.commands.manager;

import com.nalivayko.pool.repair_shop.controller.commands.Command;
import com.nalivayko.pool.repair_shop.services.FeedbackService;
import com.nalivayko.pool.repair_shop.util.ParametersAndAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @see Command
 */
public class DeleteFeedback implements Command {
    private FeedbackService feedbackService;
    private Command openAboutPage;

    public DeleteFeedback(FeedbackService feedbackService, Command openFeedbackPage) {
        this.feedbackService = feedbackService;
        this.openAboutPage = openFeedbackPage;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int deleteId = Integer.parseInt(request.getParameter(ParametersAndAttributes.DELETE_BTN));
        feedbackService.delete(deleteId);
        openAboutPage.execute(request, response);
    }
}
