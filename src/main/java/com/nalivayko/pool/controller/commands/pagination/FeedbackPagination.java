package com.nalivayko.pool.controller.commands.pagination;

import com.nalivayko.pool.model.Feedback;
import com.nalivayko.pool.services.FeedbackService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class FeedbackPagination extends Pagination<FeedbackService> {
    private int recordsPerPage;

    public FeedbackPagination(int recordsPerPage) {
        this.recordsPerPage = recordsPerPage;
    }

    @Override
    public void paginate(HttpServletRequest request, FeedbackService service) {
        int currentPage = getCurrentPage(request);
        List<Feedback> list = service.getAll(recordsPerPage, (currentPage - 1) * recordsPerPage);
        setPaginationAttributes(request, list, service.getRecordsCount(), recordsPerPage, currentPage);
    }
}
