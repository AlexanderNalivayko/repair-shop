package com.nalivayko.pool.controller.commands.about;

import com.nalivayko.pool.controller.commands.Command;
import com.nalivayko.pool.model.Feedback;
import com.nalivayko.pool.services.FeedbackService;
import com.nalivayko.pool.util.PagesPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OpenAboutPage implements Command {

    private static final String FEEDBACK = "feedback";
    private static final String NUMBER_OF_PAGES = "numberOfPages";
    private static final String CURRENT_PAGE = "currentPage";

    private static final int RECORDS_PER_PAGE = 10;

    private FeedbackService feedbackService;

    public OpenAboutPage(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int currentPage = 1;

        if (request.getParameter("page") != null) {
            currentPage = Integer.parseInt(request.getParameter("page"));
        }

        List<Feedback> list = feedbackService.getAll(RECORDS_PER_PAGE, (currentPage - 1) * RECORDS_PER_PAGE);
        int recordsCount = feedbackService.getRecordsCount();
        int numberOfPages = (int) Math.ceil(recordsCount * 1.0 / RECORDS_PER_PAGE);

        request.setAttribute(FEEDBACK, list);
        request.setAttribute(NUMBER_OF_PAGES, numberOfPages);
        request.setAttribute(CURRENT_PAGE, currentPage);
        request.getRequestDispatcher(PagesPath.ABOUT).forward(request, response);
    }
}
