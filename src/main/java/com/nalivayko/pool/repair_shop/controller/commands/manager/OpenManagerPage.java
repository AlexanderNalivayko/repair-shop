//package com.nalivayko.pool.repair_shop.controller.commands.manager;
//
//import com.nalivayko.pool.repair_shop.controller.commands.Command;
//import com.nalivayko.pool.repair_shop.controller.commands.pagination.AbstractPagination;
//import com.nalivayko.pool.repair_shop.services.RepairRequestService;
//import com.nalivayko.pool.repair_shop.util.PagesPath;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * Process request to open manager page
// * @see Command
// */
//public class OpenManagerPage implements Command {
//    private RepairRequestService repairRequestService;
//    private AbstractPagination<RepairRequestService> pagination;
//
//    public OpenManagerPage(RepairRequestService repairRequestService, AbstractPagination<RepairRequestService> pagination) {
//        this.repairRequestService = repairRequestService;
//        this.pagination = pagination;
//    }
//
//    @Override
//    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        pagination.paginate(request, repairRequestService);
//        request.getRequestDispatcher(PagesPath.MANAGER).forward(request, response);
//    }
//}
