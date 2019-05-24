package com.nalivayko.pool.controller.commands;

import com.nalivayko.pool.model.enums.UserRole;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public abstract class Command {

    private List<UserRole> permittedUsers;

    public Command(UserRole... permittedUsers) {
            this.permittedUsers = Arrays.asList(permittedUsers);
    }

    public abstract void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    public List<UserRole> getPermittedUsers() {
        return permittedUsers;
    }
}
