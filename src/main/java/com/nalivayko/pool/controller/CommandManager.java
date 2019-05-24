package com.nalivayko.pool.controller;

import com.nalivayko.pool.controller.commands.Command;
import com.nalivayko.pool.controller.commands.OpenHomePage;
import com.nalivayko.pool.controller.commands.user.OpenLoginPage;
import com.nalivayko.pool.model.User;
import com.nalivayko.pool.model.enums.UserRole;
import com.nalivayko.pool.util.Pages;
import com.nalivayko.pool.util.UrlRequests;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandManager {

    private Map<String, Command> commands = new HashMap<>();

    public CommandManager() {

        final OpenHomePage openHomePage = new OpenHomePage();

        commands.put("", openHomePage);
        commands.put(UrlRequests.HOME_PAGE, openHomePage);
        commands.put(UrlRequests.LOGIN_PAGE, new OpenLoginPage());
        commands.put(UrlRequests.LOGIN_PAGE_LOGIN, new OpenLoginPage());
    }

    /**
     * choose and perform command that represents user request
     *
     * @param request  that should be passed from dispatcherServlet
     * @param response that should be passed from dispatcherServlet
     */
    public void perform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI().replace(UrlRequests.SITE, "");
        Command command = commands.get(uri);
        if (command == null) {
            response.sendRedirect(Pages.ERROR_404);
        } else {
            User user = (User) request.getSession().getAttribute("user");
            if (commandIsPermittedForUser(command, user)) {
                command.execute(request, response);
            } else {
                request.setAttribute("msg", "you need to.."); //todo
                request.getRequestDispatcher(Pages.LOGIN).forward(request, response);
            }
        }
    }

    /**
     * check if current user is permitted
     * to perform such request
     *
     * @param command - command that represent users request
     * @param user    - current user (can be null)
     * @return true - if yes / false - if no
     */
    private boolean commandIsPermittedForUser(Command command, User user) {
        List<UserRole> permittedUsers = command.getPermittedUsers();
        if (permittedUsers == null || permittedUsers.isEmpty()) {
            return user == null;
        } else {
            if (user == null) {
                return false;
            } else {
                return permittedUsers.contains(user.getUserRole());
            }
        }
    }
}
