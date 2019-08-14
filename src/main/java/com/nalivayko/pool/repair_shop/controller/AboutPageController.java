package com.nalivayko.pool.repair_shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AboutPageController {

    @RequestMapping(value = "/about_page", method = RequestMethod.GET)
    public String openAboutPage() {
        return "about";
    }
}
