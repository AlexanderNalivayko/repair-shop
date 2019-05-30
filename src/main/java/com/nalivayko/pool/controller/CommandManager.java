package com.nalivayko.pool.controller;

import com.nalivayko.pool.controller.commands.Command;
import com.nalivayko.pool.controller.commands.about.LeaveFeedback;
import com.nalivayko.pool.controller.commands.about.OpenAboutPage;
import com.nalivayko.pool.controller.commands.repair.OpenRepairPage;
import com.nalivayko.pool.controller.commands.site.OpenHomePage;
import com.nalivayko.pool.controller.commands.user.*;
import com.nalivayko.pool.services.FeedbackService;
import com.nalivayko.pool.services.RepairRequestService;
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

    public CommandManager(UserService userService, FeedbackService feedbackService,
                          RepairRequestService repairRequestService) {

        OpenHomePage openHomePage = new OpenHomePage();
        OpenAboutPage openAboutPage = new OpenAboutPage(feedbackService);
        OpenLoginPage openLoginPage = new OpenLoginPage();

        commands.put("", openHomePage);
        commands.put(UrlRequests.HOME_PAGE, openHomePage);

        commands.put(UrlRequests.ABOUT_PAGE, openAboutPage);
        commands.put(UrlRequests.ABOUT_PAGE_FEEDBACK, new LeaveFeedback(feedbackService, openAboutPage));

        commands.put(UrlRequests.LOGIN_PAGE, openLoginPage);
        commands.put(UrlRequests.LOGIN, new Login(userService, openHomePage, openLoginPage));
        commands.put(UrlRequests.SIGN_UP_PAGE, new OpenSignUpPage());
        commands.put(UrlRequests.SIGN_UP, new SignUp(userService, openHomePage));
        commands.put(UrlRequests.LOGOUT, new Logout());

        commands.put(UrlRequests.REPAIR_PAGE, new OpenRepairPage(repairRequestService, openLoginPage));

        commands.put(UrlRequests.VALIDATE_USERNAME, new ValidateUsername(userService));
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
