package com.nalivayko.pool.repair_shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SignUpPage {

    @RequestMapping(value = "/sign_up_page", method = RequestMethod.GET)
    public String openSignUpPage() {
        return "sign_up";
    }
}
