package com.nalivayko.pool.controller.commands.site;

import com.nalivayko.pool.controller.commands.Command;
import com.nalivayko.pool.util.PagesPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OpenHomePage extends Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(PagesPath.HOME).forward(request, response);
    }
}
