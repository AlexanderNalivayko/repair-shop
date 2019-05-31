package com.nalivayko.pool.controller.commands.master;

import com.nalivayko.pool.controller.commands.Command;
import com.nalivayko.pool.model.RepairRequest;
import com.nalivayko.pool.model.enums.RepairRequestStatus;
import com.nalivayko.pool.model.enums.ReviewStatus;
import com.nalivayko.pool.services.RepairRequestService;
import com.nalivayko.pool.util.PagesPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OpenMasterPage implements Command {

    private static final String REPAIR_REQUESTS = "repairRequests";

    private RepairRequestService repairRequestService;

    public OpenMasterPage(RepairRequestService repairRequestService) {
        this.repairRequestService = repairRequestService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<RepairRequest> repairRequests = repairRequestService.getAllByReviewAndRequestStatus(ReviewStatus.ACCEPTED,
                RepairRequestStatus.REVIEWED);
        request.setAttribute(REPAIR_REQUESTS, repairRequests);
        request.getRequestDispatcher(PagesPath.MASTER).forward(request, response);
    }
}
