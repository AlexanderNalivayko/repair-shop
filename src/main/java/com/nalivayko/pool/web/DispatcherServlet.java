package com.nalivayko.pool.web;

import com.nalivayko.pool.controller.CommandManager;
import com.nalivayko.pool.persistance.DefaultTransactionManager;
import com.nalivayko.pool.persistance.TransactionManager;
import com.nalivayko.pool.persistance.dao.FeedbackDAO;
import com.nalivayko.pool.persistance.dao.ItemDAO;
import com.nalivayko.pool.persistance.dao.RepairRequestDAO;
import com.nalivayko.pool.persistance.dao.UserDAO;
import com.nalivayko.pool.persistance.dao.sql.FeedbackSqlDAO;
import com.nalivayko.pool.persistance.dao.sql.ItemSqlDAO;
import com.nalivayko.pool.persistance.dao.sql.RepairRequestSqlDAO;
import com.nalivayko.pool.persistance.dao.sql.UserSqlDAO;
import com.nalivayko.pool.persistance.dbcp.ConnectionManager;
import com.nalivayko.pool.persistance.dbcp.MySqlConnectionManager;
import com.nalivayko.pool.services.*;

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
        UserService userService = new DefaultUserService(userDAO);

        FeedbackDAO feedbackDAO = new FeedbackSqlDAO(transactionManager);
        FeedbackService feedbackService = new DefaultFeedbackService(transactionManager, feedbackDAO);

        RepairRequestDAO repairRequestDAO = new RepairRequestSqlDAO(transactionManager);
        ItemDAO itemDAO = new ItemSqlDAO(transactionManager);
        RepairRequestService repairRequestService = new DefaultRepairRequestService(repairRequestDAO, itemDAO,
                transactionManager);

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
