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

    private static final String PAGE = "page";
    private static final String NUMBER_OF_PAGES = "numberOfPages";
    private static final String CURRENT_PAGE = "currentPage";
    private static final int RECORDS_PER_PAGE = 5;
    private static final String REPAIR_REQUEST = "repairRequests";

    private RepairRequestService repairRequestService;

    public OpenManagerPage(RepairRequestService repairRequestService) {
        this.repairRequestService = repairRequestService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int currentPage = 1;
        if (request.getParameter(PAGE) != null) {
            currentPage = Integer.parseInt(request.getParameter(PAGE));
        }
        List<RepairRequest> repairRequests = repairRequestService.getAllWithStatus(RepairRequestStatus.NEW,
                RECORDS_PER_PAGE,
                (currentPage - 1) * RECORDS_PER_PAGE);
        int recordsCount = repairRequestService.countRequestsWithStatus(RepairRequestStatus.NEW);
        int numberOfPages = (int) Math.ceil(recordsCount * 1.0 / RECORDS_PER_PAGE);

        request.setAttribute(NUMBER_OF_PAGES, numberOfPages);
        request.setAttribute(CURRENT_PAGE, currentPage);
        request.setAttribute(REPAIR_REQUEST, repairRequests);
        request.getRequestDispatcher(PagesPath.MANAGER).forward(request, response);
    }
}
