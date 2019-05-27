package com.nalivayko.pool.controller.commands.user;

import com.nalivayko.pool.controller.commands.Command;
import com.nalivayko.pool.model.User;
import com.nalivayko.pool.services.UserService;
import com.nalivayko.pool.util.PagesPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Login implements Command {

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String WRONG_INPUT = "wrong_input";

    private UserService userService;

    public Login(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(LOGIN);
        String pass = request.getParameter(PASSWORD);
        User user = userService.validate(login, pass);
        if (user == null) {
            request.setAttribute(WRONG_INPUT, true);
            request.getRequestDispatcher(PagesPath.LOGIN).forward(request, response);
        } else {
            request.getSession().setAttribute("user", user);
            request.getRequestDispatcher(PagesPath.HOME).forward(request, response);
        }
    }
}
