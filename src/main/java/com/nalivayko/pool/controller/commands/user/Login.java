package com.nalivayko.pool.controller.commands.user;

import com.nalivayko.pool.controller.commands.Command;
import com.nalivayko.pool.model.enums.UserRole;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Login extends Command {
    public Login(UserRole... permittedUsers) {
        super(permittedUsers);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.
    }
}
