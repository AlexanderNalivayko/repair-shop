package com.nalivayko.pool.util;

public class PagesPath {

    private PagesPath() {
    }

    private static final String ROOT = "/WEB-INF/pages";
    private static final String ERROR = "/error";

    public static final String HOME = ROOT + "/home.jsp";
    public static final String ABOUT = ROOT + "/about.jsp";
    public static final String REPAIR = ROOT + "/repair/page.jsp";
    public static final String LOGIN = ROOT + "/login.jsp";
    public static final String SIGN_UP = ROOT + "/sign_up.jsp";
    public static final String MANAGER = ROOT + "/manager.jsp";
    public static final String MASTER = ROOT + "/master.jsp";
}
