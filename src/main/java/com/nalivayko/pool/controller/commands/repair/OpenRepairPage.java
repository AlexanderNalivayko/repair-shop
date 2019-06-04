package com.nalivayko.pool.controller.commands.repair;

import com.nalivayko.pool.controller.commands.Command;
import com.nalivayko.pool.controller.commands.pagination.Pagination;
import com.nalivayko.pool.services.RepairRequestService;
import com.nalivayko.pool.util.PagesPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @see Command
 */
public class OpenRepairPage implements Command {
    private RepairRequestService repairRequestService;
    private Pagination<RepairRequestService> pagination;

    public OpenRepairPage(RepairRequestService repairRequestService, Pagination<RepairRequestService> pagination) {
        this.repairRequestService = repairRequestService;
        this.pagination = pagination;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        pagination.paginate(request, repairRequestService);
        request.getRequestDispatcher(PagesPath.REPAIR).forward(request, response);

    }
}
