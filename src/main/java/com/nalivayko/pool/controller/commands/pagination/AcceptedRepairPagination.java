package com.nalivayko.pool.controller.commands.pagination;

import com.nalivayko.pool.model.RepairRequest;
import com.nalivayko.pool.model.enums.RepairRequestStatus;
import com.nalivayko.pool.model.enums.ReviewStatus;
import com.nalivayko.pool.services.RepairRequestService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AcceptedRepairPagination extends Pagination<RepairRequestService> {
    private int recordsPerPage;

    public AcceptedRepairPagination(int recordsPerPage) {
        this.recordsPerPage = recordsPerPage;
    }

    @Override
    public void paginate(HttpServletRequest request, RepairRequestService service) {
        int currentPage = getCurrentPage(request);
        List<RepairRequest> repairRequests = service.getAllByReviewAndRequestStatus(ReviewStatus.ACCEPTED,
                RepairRequestStatus.REVIEWED, recordsPerPage, (currentPage - 1) * recordsPerPage);
        int recordsCount = service.countRequestsWithStatus(ReviewStatus.ACCEPTED, RepairRequestStatus.REVIEWED);
        setPaginationAttributes(request, repairRequests, recordsCount, recordsPerPage, currentPage);
    }
}
