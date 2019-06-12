package com.nalivayko.pool.repair_shop.controller.commands.pagination;

import com.nalivayko.pool.repair_shop.model.Feedback;
import com.nalivayko.pool.repair_shop.services.FeedbackService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @see AbstractPagination
 */
public class FeedbackPagination extends AbstractPagination<FeedbackService> {
    private int recordsPerPage;

    public FeedbackPagination(int recordsPerPage) {
        this.recordsPerPage = recordsPerPage;
    }

    @Override
    public void paginate(HttpServletRequest request, FeedbackService service) {
        int currentPage = getCurrentPage(request);
        List<Feedback> list = service.getAll(recordsPerPage, (currentPage - 1) * recordsPerPage);
        setPaginationAttributes(request, list, service.countAll(), recordsPerPage, currentPage);
    }
}
