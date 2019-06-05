package com.nalivayko.pool.controller.commands.pagination;

import com.nalivayko.pool.model.RepairRequest;
import com.nalivayko.pool.services.RepairRequestService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @see AbstractPagination
 */
public class AcceptedRepairsPagination extends AbstractPagination<RepairRequestService> {
    private int recordsPerPage;

    public AcceptedRepairsPagination(int recordsPerPage) {
        this.recordsPerPage = recordsPerPage;
    }

    @Override
    public void paginate(HttpServletRequest request, RepairRequestService service) {
        int currentPage = getCurrentPage(request);
        List<RepairRequest> repairRequests = service.getAllAccepted(recordsPerPage, (currentPage - 1) * recordsPerPage);
        int recordsCount = service.countAccepted();
        setPaginationAttributes(request, repairRequests, recordsCount, recordsPerPage, currentPage);
    }
}
