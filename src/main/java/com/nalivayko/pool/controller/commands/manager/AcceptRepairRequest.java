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

    public AcceptRepairRequest(Command openManagerPage, RepairRequestService repairRequestService) {
        this.openManagerPage = openManagerPage;
        this.repairRequestService = repairRequestService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int repairId = Integer.parseInt(request.getParameter(REPAIR_ID));
        Integer repairPrice = Integer.parseInt(request.getParameter(PRICE));
        repairRequestService.acceptRepairRequest(repairId, repairPrice);
        openManagerPage.execute(request, response);
    }
}
