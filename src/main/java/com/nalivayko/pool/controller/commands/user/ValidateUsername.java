package com.nalivayko.pool.controller.commands.user;

import com.nalivayko.pool.controller.commands.Command;
import com.nalivayko.pool.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @see Command
 */
public class ValidateUsername  implements Command {
    private static final String LOGIN = "login";
    private static final String FREE = "free";
    private static final String TAKEN = "taken";

    private UserService userService;

    public ValidateUsername(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        if (userService.getUserByUsername(request.getParameter(LOGIN)) == null) {
            out.print(FREE);
        } else {
            out.print(TAKEN);
        }
    }
}
