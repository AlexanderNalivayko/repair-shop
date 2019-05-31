package com.nalivayko.pool.controller.commands.manager;

import com.nalivayko.pool.controller.commands.Command;
import com.nalivayko.pool.model.enums.ReviewStatus;
import com.nalivayko.pool.services.RepairRequestService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReviewRepairRequest implements Command {

    private static final String BUTTON_NAME = "btn";
    private static final String DECLINE = "decline";
    private static final String APPROVE = "approve";
    private static final String PRICE = "price";
    private static final String REPAIR_ID = "repairId";

    private Command openManagerPage;
    private RepairRequestService repairRequestService;

    public ReviewRepairRequest(Command openManagerPage, RepairRequestService repairRequestService) {
        this.openManagerPage = openManagerPage;
        this.repairRequestService = repairRequestService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int repairId = Integer.parseInt(request.getParameter(REPAIR_ID));
        String price = request.getParameter(PRICE);
        Integer repairRequestPrice;

        if (price == null || price.isEmpty()) {
            repairRequestPrice = null;
        } else {
            repairRequestPrice = Integer.parseInt(price);
        }

        if (request.getParameter(BUTTON_NAME).equals(DECLINE)) {
            repairRequestService.updateReview(repairId, ReviewStatus.DECLINED, repairRequestPrice);
        } else if (request.getParameter(BUTTON_NAME).equals(APPROVE)){
            repairRequestService.updateReview(repairId, ReviewStatus.ACCEPTED, repairRequestPrice);
        }
        openManagerPage.execute(request, response);
    }
}
