package com.nalivayko.pool.controller.commands.manager;

import com.nalivayko.pool.controller.commands.Command;
import com.nalivayko.pool.model.RepairRequest;
import com.nalivayko.pool.model.enums.RepairRequestStatus;
import com.nalivayko.pool.services.RepairRequestService;
import com.nalivayko.pool.util.PagesPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OpenManagerPage implements Command {

    private static final String REPAIR_REQUEST = "repairRequests";

    private RepairRequestService repairRequestService;

    public OpenManagerPage(RepairRequestService repairRequestService) {
        this.repairRequestService = repairRequestService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<RepairRequest> repairRequests = repairRequestService.getAllWithStatus(RepairRequestStatus.NEW);
        request.setAttribute(REPAIR_REQUEST, repairRequests);
        request.getRequestDispatcher(PagesPath.MANAGER).forward(request, response);
    }
}
