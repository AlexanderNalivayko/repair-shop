package com.nalivayko.pool.repair_shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class LoginPageController {

    @RequestMapping(value = "/login_page", method = RequestMethod.GET)
    public String openLoginPage() {
        return "login";
    }
}
