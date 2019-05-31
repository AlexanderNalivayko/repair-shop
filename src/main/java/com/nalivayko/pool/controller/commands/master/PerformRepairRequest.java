package com.nalivayko.pool.controller.commands.master;

import com.nalivayko.pool.controller.commands.Command;
import com.nalivayko.pool.model.User;
import com.nalivayko.pool.model.enums.RepairRequestStatus;
import com.nalivayko.pool.model.enums.UserRole;
import com.nalivayko.pool.services.RepairRequestService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PerformRepairRequest implements Command {

    private static final String REPAIR_REQUEST_ID = "repairId";
    private static final String USER = "user";

    private RepairRequestService repairRequestService;
    private Command openMasterPage;

    public PerformRepairRequest(RepairRequestService repairRequestService, Command openMasterPage) {
        this.repairRequestService = repairRequestService;
        this.openMasterPage = openMasterPage;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String repairId = request.getParameter(REPAIR_REQUEST_ID);
        User user = (User) request.getSession().getAttribute(USER);

        if (repairId == null || repairId.isEmpty() || user == null || user.getUserRole() != UserRole.MASTER) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        } else {
            int id = Integer.parseInt(repairId);
            repairRequestService.updateStatus(id, RepairRequestStatus.DONE);
            openMasterPage.execute(request, response);
        }
    }
}
