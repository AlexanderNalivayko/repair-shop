package com.nalivayko.pool.controller;

import com.nalivayko.pool.controller.commands.Command;
import com.nalivayko.pool.controller.commands.about.LeaveFeedback;
import com.nalivayko.pool.controller.commands.about.OpenAboutPage;
import com.nalivayko.pool.controller.commands.manager.OpenManagerPage;
import com.nalivayko.pool.controller.commands.manager.ReviewRepairRequest;
import com.nalivayko.pool.controller.commands.manager.DeleteFeedback;
import com.nalivayko.pool.controller.commands.master.OpenMasterPage;
import com.nalivayko.pool.controller.commands.master.PerformRepairRequest;
import com.nalivayko.pool.controller.commands.pagination.*;
import com.nalivayko.pool.controller.commands.repair.CreateRepairRequest;
import com.nalivayko.pool.controller.commands.repair.OpenRepairPage;
import com.nalivayko.pool.controller.commands.OpenHomePage;
import com.nalivayko.pool.controller.commands.user.*;
import com.nalivayko.pool.services.FeedbackService;
import com.nalivayko.pool.services.RepairRequestService;
import com.nalivayko.pool.services.UserService;
import com.nalivayko.pool.util.UrlRequests;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    private static final int FEEDBACK_PER_PAGE = 10;
    private static final int REPAIRS_PER_PAGE = 5;

    private Map<String, Command> commands = new HashMap<>();


    public CommandManager(UserService userService, FeedbackService feedbackService,
                          RepairRequestService repairRequestService) {

        Pagination<FeedbackService> feedbackPagination = new FeedbackPagination(FEEDBACK_PER_PAGE);
        Pagination<RepairRequestService> newRepairPagination = new NewRepairPagination(REPAIRS_PER_PAGE);
        Pagination<RepairRequestService> userRepairPagination = new UserRepairPagination(REPAIRS_PER_PAGE);
        Pagination<RepairRequestService> acceptedRepairsPagination = new AcceptedRepairPagination(REPAIRS_PER_PAGE);

        OpenHomePage openHomePage = new OpenHomePage();
        OpenAboutPage openAboutPage = new OpenAboutPage(feedbackService, feedbackPagination);
        OpenLoginPage openLoginPage = new OpenLoginPage();
        OpenRepairPage openRepairPage = new OpenRepairPage(repairRequestService, userRepairPagination);
        OpenManagerPage openManagerPage = new OpenManagerPage(repairRequestService, newRepairPagination);
        OpenMasterPage openMasterPage = new OpenMasterPage(repairRequestService, acceptedRepairsPagination);

        commands.put("", openHomePage);
        commands.put(UrlRequests.HOME_PAGE, openHomePage);

        commands.put(UrlRequests.ABOUT_PAGE, openAboutPage);
        commands.put(UrlRequests.ABOUT_PAGE_FEEDBACK, new LeaveFeedback(feedbackService, openAboutPage));

        commands.put(UrlRequests.LOGIN_PAGE, openLoginPage);
        commands.put(UrlRequests.LOGIN, new Login(userService, openHomePage, openLoginPage));
        commands.put(UrlRequests.SIGN_UP_PAGE, new OpenSignUpPage());
        commands.put(UrlRequests.SIGN_UP, new SignUp(userService, openHomePage));
        commands.put(UrlRequests.LOGOUT, new Logout());

        commands.put(UrlRequests.REPAIR_PAGE, openRepairPage);
        commands.put(UrlRequests.REPAIR_PAGE_CREATE, new CreateRepairRequest(repairRequestService, openRepairPage));

        commands.put(UrlRequests.MANAGER_PAGE, openManagerPage);
        commands.put(UrlRequests.MANAGER_PAGE_REVIEW, new ReviewRepairRequest(openManagerPage, repairRequestService));

        commands.put(UrlRequests.MASTER_PAGE, openMasterPage);
        commands.put(UrlRequests.MASTER_PAGE_PERFORM, new PerformRepairRequest(repairRequestService, openMasterPage));
        commands.put(UrlRequests.MASTER_DELETE_FEEDBACK, new DeleteFeedback(feedbackService, openAboutPage));

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
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        } else {
            command.execute(request, response);
        }
    }
}
