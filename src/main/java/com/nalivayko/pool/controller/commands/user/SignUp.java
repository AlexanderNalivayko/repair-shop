package com.nalivayko.pool.controller.commands.user;

import com.nalivayko.pool.controller.commands.Command;
import com.nalivayko.pool.model.User;
import com.nalivayko.pool.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @see Command
 */
public class SignUp implements Command {
    private static final String LOGIN = "login";
    private static final String PASS = "pass";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String EMAIL = "email";
    private static final String PHONE = "phone";

    private static final String USER = "user";

    private UserService userService;
    private Command openHomePageCommand;

    public SignUp(UserService userService, Command openHomePageCommand) {
        this.userService = userService;
        this.openHomePageCommand = openHomePageCommand;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(LOGIN);
        String pass = request.getParameter(PASS);
        String firstName = request.getParameter(FIRST_NAME);
        String lastName = request.getParameter(LAST_NAME);
        String email = request.getParameter(EMAIL);
        String phone = request.getParameter(PHONE);
        userService.create(login, pass, firstName, lastName, email, phone);

        User user = userService.getUserByUsername(login);
        request.getSession().setAttribute(USER, user);
        openHomePageCommand.execute(request, response);
    }
}
