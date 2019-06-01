package com.nalivayko.pool.controller.commands.repair;

import com.nalivayko.pool.controller.commands.Command;
import com.nalivayko.pool.model.Feedback;
import com.nalivayko.pool.model.RepairRequest;
import com.nalivayko.pool.model.User;
import com.nalivayko.pool.services.RepairRequestService;
import com.nalivayko.pool.util.PagesPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OpenRepairPage implements Command {

    private Command openLoginPage;
    private RepairRequestService repairRequestService;

    public OpenRepairPage(RepairRequestService repairRequestService, Command openLoginPage) {
        this.repairRequestService = repairRequestService;
        this.openLoginPage = openLoginPage;
    }

    private static final String NUMBER_OF_PAGES = "numberOfPages";
    private static final String CURRENT_PAGE = "currentPage";
    private static final int RECORDS_PER_PAGE = 5;

    private static final  String PAGE = "page";
    private static final String NEW_REQUEST = "new_request";
    private static final String MY_REQUESTS = "my_requests";
    private static final String TAB = "tab";
    private static final String REPAIR_REQUEST = "repairRequests";
    private static final String USER = "user";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int currentPage = 1;


        if (request.getParameter(PAGE) == null) {
            request.setAttribute(TAB, NEW_REQUEST);
        } else {
            currentPage = Integer.parseInt(request.getParameter(PAGE));
            request.setAttribute(TAB, MY_REQUESTS);
        }

        User user = (User) request.getSession().getAttribute(USER);
        List<RepairRequest> repairRequests = repairRequestService.getRepairRequestsByUserId(user.getId(), RECORDS_PER_PAGE, (currentPage - 1) * RECORDS_PER_PAGE);
        int recordsCount = repairRequestService.countNumberOfRequestsForUser(user.getId());
        int numberOfPages = (int) Math.ceil(recordsCount * 1.0 / RECORDS_PER_PAGE);
        request.setAttribute(NUMBER_OF_PAGES, numberOfPages);
        request.setAttribute(CURRENT_PAGE, currentPage);
        request.setAttribute(REPAIR_REQUEST, repairRequests);
        request.getRequestDispatcher(PagesPath.REPAIR).forward(request, response);

    }
}
