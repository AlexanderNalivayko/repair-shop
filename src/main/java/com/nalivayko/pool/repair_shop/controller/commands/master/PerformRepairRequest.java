package com.nalivayko.pool.repair_shop.controller.commands.master;

import com.nalivayko.pool.repair_shop.controller.commands.Command;
import com.nalivayko.pool.repair_shop.services.RepairRequestService;
import com.nalivayko.pool.repair_shop.util.ParametersAndAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Process request to perform repair request
 * @see Command
 */
public class PerformRepairRequest implements Command {
    private RepairRequestService repairRequestService;
    private Command openMasterPage;

    public PerformRepairRequest(RepairRequestService repairRequestService, Command openMasterPage) {
        this.repairRequestService = repairRequestService;
        this.openMasterPage = openMasterPage;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String repairId = request.getParameter(ParametersAndAttributes.REPAIR_REQUEST_ID);
        if (repairId == null || repairId.isEmpty()) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        } else {
            repairRequestService.performRepairRequest(Integer.parseInt(repairId));
            openMasterPage.execute(request, response);
        }
    }
}
