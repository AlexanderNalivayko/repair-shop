package com.nalivayko.pool.controller.commands.repair;

import com.nalivayko.pool.controller.commands.Command;
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

    private static final String USER = "user";
    private static final String NEED_LOGIN = "need_login";
    private static final String REPAIR_REQUEST = "repairRequests";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute(USER);
        if (user == null) {
            request.setAttribute(NEED_LOGIN, true);
            openLoginPage.execute(request, response);
        } else {
            List<RepairRequest> repairRequests = repairRequestService.getRepairRequestsByUserId(user.getId());
            request.setAttribute(REPAIR_REQUEST, repairRequests);
            request.getRequestDispatcher(PagesPath.REPAIR).forward(request, response);
        }
    }
}
