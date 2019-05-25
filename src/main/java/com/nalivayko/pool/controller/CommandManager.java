package com.nalivayko.pool.controller;

import com.nalivayko.pool.controller.commands.Command;
import com.nalivayko.pool.controller.commands.site.OpenAboutPage;
import com.nalivayko.pool.controller.commands.site.OpenHomePage;
import com.nalivayko.pool.controller.commands.user.Login;
import com.nalivayko.pool.controller.commands.user.OpenLoginPage;
import com.nalivayko.pool.services.FeedbackService;
import com.nalivayko.pool.services.UserService;
import com.nalivayko.pool.util.PagesPath;
import com.nalivayko.pool.util.UrlRequests;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    private Map<String, Command> commands = new HashMap<>();

    public CommandManager(UserService userService, FeedbackService feedbackService) {
        final OpenHomePage openHomePage = new OpenHomePage();
        commands.put("", openHomePage);
        commands.put(UrlRequests.HOME_PAGE, openHomePage);
        commands.put(UrlRequests.ABOUT, new OpenAboutPage(feedbackService));
        commands.put(UrlRequests.LOGIN_PAGE, new OpenLoginPage());
        commands.put(UrlRequests.LOGIN_PAGE_LOGIN, new Login(userService));
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
            response.sendRedirect(PagesPath.ERROR_404);
        } else {
            command.execute(request, response);
        }
    }
}
