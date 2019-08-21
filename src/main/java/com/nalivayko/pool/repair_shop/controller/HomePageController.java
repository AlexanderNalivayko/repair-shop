package com.nalivayko.pool.repair_shop.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class HomePageController {

    @RequestMapping(value = {"/home_page", "/", ""}, method = RequestMethod.GET)
    public String openHomePage(Authentication authentication){
        return "home";
    }
}
