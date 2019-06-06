package com.nalivayko.pool.controller.commands;

import com.nalivayko.pool.util.PagesPath;
import com.nalivayko.pool.util.UrlRequests;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OpenHomePage implements Command {

    private static final String HOME_PAGE_URL =  UrlRequests.SITE + UrlRequests.HOME_PAGE;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getRequestURI().equals(request.getContextPath() + HOME_PAGE_URL)) {
            request.getRequestDispatcher(PagesPath.HOME).forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + HOME_PAGE_URL);
        }
    }
}
