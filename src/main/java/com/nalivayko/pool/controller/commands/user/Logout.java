package com.nalivayko.pool.controller.commands.user;

import com.nalivayko.pool.controller.commands.Command;
import com.nalivayko.pool.util.PagesPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @see Command
 */
public class Logout implements Command {
    private static final String USER = "user";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute(USER);
        request.getRequestDispatcher(PagesPath.HOME).forward(request, response);
    }
}
