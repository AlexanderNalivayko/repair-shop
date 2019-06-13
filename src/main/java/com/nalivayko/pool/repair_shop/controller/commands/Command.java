package com.nalivayko.pool.repair_shop.controller.commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Classes that implement this interface should provide processing of specific requests
 * Implementations should process data and/or perform forward to some resource
 */
public interface Command {

    void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;
}
