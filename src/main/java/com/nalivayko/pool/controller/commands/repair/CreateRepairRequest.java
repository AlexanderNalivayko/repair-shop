package com.nalivayko.pool.controller.commands.repair;

import com.nalivayko.pool.controller.commands.Command;
import com.nalivayko.pool.model.User;
import com.nalivayko.pool.services.RepairRequestService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateRepairRequest implements Command {

    private static final String USER = "user";

    private RepairRequestService repairRequestService;
    private Command openRepairRequests;

    public CreateRepairRequest(RepairRequestService repairRequestService, Command openRepairRequests) {
        this.repairRequestService = repairRequestService;
        this.openRepairRequests = openRepairRequests;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute(USER);
        if (user == null) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        } else {
            String type = request.getParameter("prod-type");
            String brand = request.getParameter("brand");
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            repairRequestService.createRepairRequest(user, type, brand, name, description);
            openRepairRequests.execute(request, response);
        }
    }
}
