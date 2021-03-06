package com.nalivayko.pool.repair_shop.controller.commands.pagination;

import com.nalivayko.pool.repair_shop.model.RepairRequest;
import com.nalivayko.pool.repair_shop.model.User;
import com.nalivayko.pool.repair_shop.services.RepairRequestService;
import com.nalivayko.pool.repair_shop.util.ParametersAndAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @see AbstractPagination
 */
public class UserRepairPagination extends AbstractPagination<RepairRequestService> {

    private int recordsPerPage;

    public UserRepairPagination(int recordsPerPage) {
        this.recordsPerPage = recordsPerPage;
    }

    @Override
    public void paginate(HttpServletRequest request, RepairRequestService service) {
        int currentPage = getCurrentPage(request);
        User user = (User) request.getSession().getAttribute(ParametersAndAttributes.USER);
        List<RepairRequest> repairRequests = service.getAllByUserId(user.getId(),
                recordsPerPage,
                (currentPage - 1) * recordsPerPage);
        int recordsCount = service.countByUserId(user.getId());
        setPaginationAttributes(request, repairRequests, recordsCount, recordsPerPage, currentPage);
    }
}
