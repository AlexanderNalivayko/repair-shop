package com.nalivayko.pool.repair_shop.controller.commands.repair;

import com.nalivayko.pool.repair_shop.controller.commands.Command;
import com.nalivayko.pool.repair_shop.controller.commands.pagination.AbstractPagination;
import com.nalivayko.pool.repair_shop.services.RepairRequestService;
import com.nalivayko.pool.repair_shop.util.PagesPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @see Command
 */
public class OpenRepairPage implements Command {
    private RepairRequestService repairRequestService;
    private AbstractPagination<RepairRequestService> pagination;

    public OpenRepairPage(RepairRequestService repairRequestService,
                          AbstractPagination<RepairRequestService> userPagination) {
        this.repairRequestService = repairRequestService;
        this.pagination = userPagination;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        pagination.paginate(request, repairRequestService);
        request.getRequestDispatcher(PagesPath.REPAIR).forward(request, response);

    }
}
