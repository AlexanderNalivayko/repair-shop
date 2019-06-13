package com.nalivayko.pool.repair_shop.controller.commands.manager;

import com.nalivayko.pool.repair_shop.controller.commands.Command;
import com.nalivayko.pool.repair_shop.services.RepairRequestService;
import com.nalivayko.pool.repair_shop.util.ParametersAndAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Process request to accept repair request
 * @see Command
 */
public class AcceptRepairRequest implements Command {
    private Command openManagerPage;
    private RepairRequestService repairRequestService;

    public AcceptRepairRequest(RepairRequestService repairRequestService, Command openManagerPage) {
        this.repairRequestService = repairRequestService;
        this.openManagerPage = openManagerPage;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String repairIdString = request.getParameter(ParametersAndAttributes.REPAIR_ID);
        String priseString = request.getParameter(ParametersAndAttributes.PRICE);
        if (repairIdString == null
                || repairIdString.isEmpty()
                || priseString == null
                || priseString.isEmpty()) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        } else {
            repairRequestService.acceptRepairRequest(repairIdString, priseString);
            openManagerPage.execute(request, response);
        }
    }
}
