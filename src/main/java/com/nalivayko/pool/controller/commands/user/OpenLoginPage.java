package com.nalivayko.pool.controller.commands.user;

import com.nalivayko.pool.controller.commands.Command;
import com.nalivayko.pool.util.PagesPath;
import com.nalivayko.pool.util.UrlRequests;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OpenLoginPage implements Command {

    private static final String LOGIN_PAGE_URL =  UrlRequests.SITE + UrlRequests.LOGIN_PAGE;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getRequestURI().equals(request.getContextPath() + LOGIN_PAGE_URL)) {
            request.getRequestDispatcher(PagesPath.LOGIN).forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + LOGIN_PAGE_URL);
        }
    }
}
