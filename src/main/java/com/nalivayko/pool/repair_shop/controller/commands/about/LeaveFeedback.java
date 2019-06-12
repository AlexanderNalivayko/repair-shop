package com.nalivayko.pool.repair_shop.controller.commands.about;

import com.nalivayko.pool.repair_shop.controller.commands.Command;
import com.nalivayko.pool.repair_shop.model.User;
import com.nalivayko.pool.repair_shop.services.FeedbackService;
import com.nalivayko.pool.repair_shop.util.ParametersAndAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @see Command
 */
public class LeaveFeedback implements Command {
    private FeedbackService feedbackService;
    private Command openAboutPage;

    public LeaveFeedback(FeedbackService feedbackService, Command openAboutPage) {
        this.feedbackService = feedbackService;
        this.openAboutPage = openAboutPage;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String feedbackText = request.getParameter(ParametersAndAttributes.FEEDBACK_MSG);
        if (feedbackText == null || feedbackText.isEmpty()) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        } else {
            User user = (User) request.getSession().getAttribute(ParametersAndAttributes.USER);
            feedbackService.create(user, feedbackText);
            openAboutPage.execute(request, response);
        }
    }
}
