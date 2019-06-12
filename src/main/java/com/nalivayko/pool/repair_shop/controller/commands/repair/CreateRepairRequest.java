package com.nalivayko.pool.controller.commands.repair;

import com.nalivayko.pool.controller.commands.Command;
import com.nalivayko.pool.model.User;
import com.nalivayko.pool.services.RepairRequestService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.nalivayko.pool.util.ParametersAndAttributes.*;

/**
 * @see Command
 */
public class CreateRepairRequest implements Command {
    private RepairRequestService repairRequestService;
    private Command openRepairRequests;

    public CreateRepairRequest(RepairRequestService repairRequestService, Command openRepairRequests) {
        this.repairRequestService = repairRequestService;
        this.openRepairRequests = openRepairRequests;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute(USER);
        String type = request.getParameter(PRODUCT_TYPE);
        String brand = request.getParameter(BRAND);
        String name = request.getParameter(NAME);
        String description = request.getParameter(DESCRIPTION);
        repairRequestService.createRepairRequest(user, type, brand, name, description);
        openRepairRequests.execute(request, response);
    }
}
