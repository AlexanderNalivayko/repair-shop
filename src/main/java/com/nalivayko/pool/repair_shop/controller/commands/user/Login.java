package com.nalivayko.pool.repair_shop.controller.commands.user;

import com.nalivayko.pool.repair_shop.controller.commands.Command;
import com.nalivayko.pool.repair_shop.model.User;
import com.nalivayko.pool.repair_shop.services.UserService;
import com.nalivayko.pool.repair_shop.util.ParametersAndAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @see Command
 */
public class Login implements Command {
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
        String login = request.getParameter(ParametersAndAttributes.LOGIN);
        String pass = request.getParameter(ParametersAndAttributes.PASSWORD);
        User user = userService.validate(login, pass);
        if (user == null) {
            request.setAttribute(ParametersAndAttributes.WRONG_INPUT, true);
            openLoginPage.execute(request, response);
        } else {
            request.getSession().setAttribute(ParametersAndAttributes.USER, user);
            openHomePage.execute(request, response);
        }
    }
}
