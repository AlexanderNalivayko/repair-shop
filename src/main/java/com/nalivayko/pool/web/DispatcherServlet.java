package com.nalivayko.pool.web;

import com.nalivayko.pool.controller.CommandManager;
import com.nalivayko.pool.persistance.DefaultTransactionManager;
import com.nalivayko.pool.persistance.TransactionManager;
import com.nalivayko.pool.persistance.dao.FeedbackDAO;
import com.nalivayko.pool.persistance.dao.sql.FeedbackSqlDAO;
import com.nalivayko.pool.persistance.dbcp.ConnectionManager;
import com.nalivayko.pool.persistance.dbcp.MySqlConnectionManager;
import com.nalivayko.pool.services.DefaultFeedbackService;
import com.nalivayko.pool.services.DefaultUserService;
import com.nalivayko.pool.services.FeedbackService;
import com.nalivayko.pool.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DispatcherServlet extends HttpServlet {

    private CommandManager commandManager;

    @Override
    public void init() {
        UserService userService = new DefaultUserService();

        ConnectionManager connectionManager = new MySqlConnectionManager();
        TransactionManager transactionManager = new DefaultTransactionManager(connectionManager);
        FeedbackDAO feedbackDAO = new FeedbackSqlDAO(transactionManager);

        FeedbackService feedbackService = new DefaultFeedbackService(transactionManager, feedbackDAO);
        commandManager = new CommandManager(userService, feedbackService); //todo provide parameters
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
