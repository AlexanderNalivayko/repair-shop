package com.nalivayko.pool.controller.commands.master;

import com.nalivayko.pool.controller.commands.Command;
import com.nalivayko.pool.services.RepairRequestService;
import com.nalivayko.pool.util.ParametersAndAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
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
