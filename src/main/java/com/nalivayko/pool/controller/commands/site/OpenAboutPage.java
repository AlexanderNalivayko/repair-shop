package com.nalivayko.pool.controller.commands.site;

import com.nalivayko.pool.controller.commands.Command;
import com.nalivayko.pool.services.FeedbackService;
import com.nalivayko.pool.util.PagesPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OpenAboutPage extends Command {

    private static final String FEEDBACK = "feedback";

    private FeedbackService feedbackService;

    public OpenAboutPage(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(FEEDBACK, feedbackService.getAll());
        request.getRequestDispatcher(PagesPath.ABOUT).forward(request, response);
    }
}
