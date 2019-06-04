package com.nalivayko.pool.controller.commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Interface for processing specific request
 * Implementations should process data and/or perform forward to some resource
 */
public interface Command {

    void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;
}
