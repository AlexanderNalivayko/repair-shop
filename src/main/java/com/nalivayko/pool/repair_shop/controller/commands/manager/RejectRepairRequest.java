package com.nalivayko.pool.repair_shop.controller.commands.manager;

import com.nalivayko.pool.repair_shop.controller.commands.Command;
import com.nalivayko.pool.repair_shop.services.RepairRequestService;
import com.nalivayko.pool.repair_shop.util.ParametersAndAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Process request to reject repair request
 * @see Command
 */
public class RejectRepairRequest implements Command {
    private Command openManagerPage;
    private RepairRequestService repairRequestService;

    public RejectRepairRequest(RepairRequestService repairRequestService, Command openManagerPage) {
        this.repairRequestService = repairRequestService;
        this.openManagerPage = openManagerPage;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String stringRepairId = request.getParameter(ParametersAndAttributes.REPAIR_ID);
        String reason = request.getParameter(ParametersAndAttributes.REASON);
        if (stringRepairId == null || stringRepairId.isEmpty() || reason == null || reason.isEmpty()) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        } else {
            repairRequestService.rejectRepairRequest(Integer.parseInt(stringRepairId), reason);
            openManagerPage.execute(request, response);
        }
    }
}

