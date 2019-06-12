package com.nalivayko.pool.controller.commands.user;

import com.nalivayko.pool.controller.commands.Command;
import com.nalivayko.pool.model.User;
import com.nalivayko.pool.services.UserService;
import com.nalivayko.pool.util.ParametersAndAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @see Command
 */
public class SignUp implements Command {
    private UserService userService;
    private Command openHomePageCommand;

    public SignUp(UserService userService, Command openHomePageCommand) {
        this.userService = userService;
        this.openHomePageCommand = openHomePageCommand;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(ParametersAndAttributes.LOGIN);
        String pass = request.getParameter(ParametersAndAttributes.PASS);
        String firstName = request.getParameter(ParametersAndAttributes.FIRST_NAME);
        String lastName = request.getParameter(ParametersAndAttributes.LAST_NAME);
        String email = request.getParameter(ParametersAndAttributes.EMAIL);
        String phone = request.getParameter(ParametersAndAttributes.PHONE);
        userService.create(login, pass, firstName, lastName, email, phone);

        User user = userService.getUserByUsername(login);
        request.getSession().setAttribute(ParametersAndAttributes.USER, user);
        openHomePageCommand.execute(request, response);
    }
}
