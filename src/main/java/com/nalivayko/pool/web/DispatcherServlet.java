package com.nalivayko.pool.web;

import com.nalivayko.pool.controller.CommandManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class DispatcherServlet extends HttpServlet {

    private CommandManager commandManager;

    @Override
    public void init() {
        commandManager = new CommandManager(null, null); //todo provide parameters
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
