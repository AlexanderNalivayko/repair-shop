package com.nalivayko.pool.controller.commands.about;

import com.nalivayko.pool.controller.commands.Command;
import com.nalivayko.pool.controller.commands.pagination.AbstractPagination;
import com.nalivayko.pool.services.FeedbackService;
import com.nalivayko.pool.util.PagesPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @see Command
 */
public class OpenAboutPage implements Command {
    private FeedbackService feedbackService;
    private AbstractPagination<FeedbackService> pagination;

    public OpenAboutPage(FeedbackService feedbackService, AbstractPagination<FeedbackService> pagination) {
        this.feedbackService = feedbackService;
        this.pagination = pagination;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        pagination.paginate(request, feedbackService);
        request.getRequestDispatcher(PagesPath.ABOUT).forward(request, response);
    }
}
