package com.nalivayko.pool.controller.commands.user;

import com.nalivayko.pool.controller.commands.Command;
import com.nalivayko.pool.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ValidateUsername  implements Command {

    private UserService userService;

    //todo refactor
    public ValidateUsername(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        if (userService.getUserByUsername(request.getParameter("login")) == null) {
            out.print("<span style=\"color:green;\">Username available</span>");
        } else {
            out.print("<span style=\"color:red;\">Username unavailable</span>");
        }
    }
}
