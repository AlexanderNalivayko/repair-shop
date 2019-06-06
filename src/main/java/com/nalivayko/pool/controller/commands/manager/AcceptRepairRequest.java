package com.nalivayko.pool.controller.commands.manager;

import com.nalivayko.pool.controller.commands.Command;
import com.nalivayko.pool.services.RepairRequestService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @see Command
 */
public class AcceptRepairRequest implements Command {
    private static final String PRICE = "price";
    private static final String REPAIR_ID = "repairId";

    private Command openManagerPage;
    private RepairRequestService repairRequestService;

    public AcceptRepairRequest(RepairRequestService repairRequestService, Command openManagerPage) {
        this.repairRequestService = repairRequestService;
        this.openManagerPage = openManagerPage;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String repairIdString = request.getParameter(REPAIR_ID);
        String priseString = request.getParameter(PRICE);
        if (repairIdString == null
                || repairIdString.isEmpty()
                || priseString == null
                || priseString.isEmpty()) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        } else {
            repairRequestService.acceptRepairRequest(Integer.parseInt(repairIdString), Integer.parseInt(priseString));
            openManagerPage.execute(request, response);
        }
    }
}
