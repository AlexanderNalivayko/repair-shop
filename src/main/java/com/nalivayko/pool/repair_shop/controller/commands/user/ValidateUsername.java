package com.nalivayko.pool.repair_shop.controller.commands.user;

import com.nalivayko.pool.repair_shop.controller.commands.Command;
import com.nalivayko.pool.repair_shop.services.UserService;
import com.nalivayko.pool.repair_shop.util.ParametersAndAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @see Command
 */
public class ValidateUsername  implements Command {

    private UserService userService;

    public ValidateUsername(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        if (userService.getUserByUsername(request.getParameter(ParametersAndAttributes.LOGIN)) == null) {
            out.print(ParametersAndAttributes.FREE);
        } else {
            out.print(ParametersAndAttributes.TAKEN);
        }
    }
}
