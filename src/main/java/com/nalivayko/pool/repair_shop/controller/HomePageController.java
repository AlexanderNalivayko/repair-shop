package com.nalivayko.pool.repair_shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomePageController {

    @RequestMapping(value = {"/home_page", "/", ""}, method = RequestMethod.GET)
    public String openHomePage(){
        return "home";
    }
}
