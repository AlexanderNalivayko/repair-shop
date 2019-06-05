package com.nalivayko.pool.util;

public class UrlRequests {
    public static final String SITE = "/site";

    public static final String HOME_PAGE = "/home_page";

    public static final String LOGIN_PAGE = "/login_page";
    public static final String LOGIN = LOGIN_PAGE + "/login";
    public static final String LOGOUT = "/logout";

    public static final String SIGN_UP_PAGE = "/sign_up_page";
    public static final String SIGN_UP = SIGN_UP_PAGE + "/sign_up";
    public static final String VALIDATE_USERNAME = "/validate_username";

    public static final String ABOUT_PAGE = "/about_page";
    public static final String ABOUT_PAGE_FEEDBACK = ABOUT_PAGE + "/leave_feedback";

    public static final String REPAIR_PAGE = "/repair_page";
    public static final String REPAIR_PAGE_CREATE = REPAIR_PAGE + "/create";

    public static final String MANAGER_PAGE = "/manager";
    public static final String MANAGER_PAGE_ACCEPT = MANAGER_PAGE + "/accept";
    public static final String MANAGER_PAGE_REJECT = MANAGER_PAGE + "/reject";
    public static final String MASTER_DELETE_FEEDBACK = MANAGER_PAGE + "/delete_feedback";

    public static final String MASTER_PAGE = "/master";
    public static final String MASTER_PAGE_PERFORM = MASTER_PAGE + "/perform";

    public static final String CUSTOMER = "/customer";

    private UrlRequests() {
    }
}
