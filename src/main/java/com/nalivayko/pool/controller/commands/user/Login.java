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

    private static final String USER = "user";

    private UserService userService;
    private Command openHomePage;
    private Command openLoginPage;

    public Login(UserService userService, Command openHomePage, Command openLoginPage) {
        this.userService = userService;
        this.openHomePage = openHomePage;
        this.openLoginPage = openLoginPage;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(LOGIN);
        String pass = request.getParameter(PASSWORD);
        User user = userService.validate(login, pass);
        if (user == null) {
            request.setAttribute(WRONG_INPUT, true);
            openLoginPage.execute(request, response);
        } else {
            request.getSession().setAttribute(USER, user);
            openHomePage.execute(request, response);
        }
    }
}
