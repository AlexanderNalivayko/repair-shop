package com.nalivayko.pool.controller.commands.about;

import com.nalivayko.pool.controller.commands.Command;
import com.nalivayko.pool.model.User;
import com.nalivayko.pool.services.FeedbackService;
import com.nalivayko.pool.util.PagesPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LeaveFeedback implements Command {

    private static final String FEEDBACK_MSG = "feedback_msg";
    private static final String USER = "user";

    private FeedbackService feedbackService;
    private Command openAboutPage;

    public LeaveFeedback(FeedbackService feedbackService, Command openAboutPage) {
        this.feedbackService = feedbackService;
        this.openAboutPage = openAboutPage;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String feedbackText = request.getParameter(FEEDBACK_MSG);
        User user = (User) request.getSession().getAttribute(USER);
        if (user == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        } else {
            feedbackService.create(user, feedbackText);
            openAboutPage.execute(request, response);
        }
    }
}
