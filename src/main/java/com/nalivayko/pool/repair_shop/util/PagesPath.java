package com.nalivayko.pool.repair_shop.util;

public class PagesPath {

    private PagesPath() {
    }

    private static final String ROOT = "/WEB-INF/pages";

    public static final String HOME = ROOT + "/openHomePage.jsp";
    public static final String ABOUT = ROOT + "/about.jsp";
    public static final String REPAIR = ROOT + "/repair/repairs.jsp";
    public static final String LOGIN = ROOT + "/login.jsp";
    public static final String SIGN_UP = ROOT + "/sign_up.jsp";
    public static final String MANAGER = ROOT + "/manager.jsp";
    public static final String MASTER = ROOT + "/master.jsp";
}
