package com.nalivayko.pool.repair_shop.controller.commands.user;

import com.nalivayko.pool.repair_shop.controller.commands.Command;
import com.nalivayko.pool.repair_shop.util.PagesPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @see Command
 */
public class OpenLoginPage implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(PagesPath.LOGIN).forward(request, response);
    }
}
