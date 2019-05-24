package com.nalivayko.pool.util;

public class Pages {

    private Pages() {
    }

    private static final String ROOT = "/pages/";

    public static final String HOME = ROOT + "home.jsp";
    public static final String LOGIN = ROOT + "login.jsp";
    public static final String ERROR_403 = ROOT + "error/403.jsp";
    public static final String ERROR_404 = ROOT + "error/404.jsp";
}
