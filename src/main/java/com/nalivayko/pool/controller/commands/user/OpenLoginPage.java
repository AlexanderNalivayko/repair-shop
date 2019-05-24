package com.nalivayko.pool.controller.commands.user;

import com.nalivayko.pool.controller.commands.Command;
import com.nalivayko.pool.model.enums.UserRole;
import com.nalivayko.pool.util.Pages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OpenLoginPage extends Command {

    public OpenLoginPage(UserRole... permittedUsers) {
        super(permittedUsers);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(Pages.LOGIN).forward(request, response);
    }
}
