package com.nalivayko.pool.repair_shop.web;

import com.nalivayko.pool.repair_shop.controller.CommandManager;
import com.nalivayko.pool.repair_shop.persistance.DefaultTransactionManager;
import com.nalivayko.pool.repair_shop.persistance.TransactionManager;
import com.nalivayko.pool.repair_shop.persistance.dao.*;
import com.nalivayko.pool.repair_shop.persistance.dao.sql.*;
import com.nalivayko.pool.repair_shop.persistance.dbcp.ConnectionManager;
import com.nalivayko.pool.repair_shop.persistance.dbcp.MySqlConnectionManager;
import com.nalivayko.pool.repair_shop.services.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DispatcherServlet extends HttpServlet {

    private CommandManager commandManager;

    @Override
    public void init() {
        ConnectionManager connectionManager = new MySqlConnectionManager();
        TransactionManager transactionManager = new DefaultTransactionManager(connectionManager);

        UserDAO userDAO = new UserSqlDAO(transactionManager);
        UserService userService = new DefaultUserService(userDAO, transactionManager);

        FeedbackDAO feedbackDAO = new FeedbackSqlDAO(transactionManager);
        FeedbackService feedbackService = new DefaultFeedbackService(transactionManager, feedbackDAO);

        RepairRequestDAO repairRequestDAO = new RepairRequestSqlDAO(transactionManager);
        ReviewDAO reviewDAO = new ReviewSqlDAO(transactionManager);
        ItemDAO itemDAO = new ItemSqlDAO(transactionManager);

        RepairRequestService repairRequestService = new DefaultRepairRequestService(reviewDAO, repairRequestDAO,
                itemDAO, transactionManager);

        commandManager = new CommandManager(userService, feedbackService, repairRequestService);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        commandManager.perform(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        commandManager.perform(req, resp);
    }
}
