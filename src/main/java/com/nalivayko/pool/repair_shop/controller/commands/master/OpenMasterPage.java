package com.nalivayko.pool.controller.commands.master;

import com.nalivayko.pool.controller.commands.Command;
import com.nalivayko.pool.controller.commands.pagination.AbstractPagination;
import com.nalivayko.pool.services.RepairRequestService;
import com.nalivayko.pool.util.PagesPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @see Command
 */
public class OpenMasterPage implements Command {
    private RepairRequestService repairRequestService;
    private AbstractPagination<RepairRequestService> pagination;

    public OpenMasterPage(RepairRequestService repairRequestService, AbstractPagination<RepairRequestService> pagination) {
        this.repairRequestService = repairRequestService;
        this.pagination = pagination;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        pagination.paginate(request, repairRequestService);
        request.getRequestDispatcher(PagesPath.MASTER).forward(request, response);
    }
}
