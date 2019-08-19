package com.nalivayko.pool.repair_shop.controller;

import com.nalivayko.pool.repair_shop.services.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AboutPageController {

    @Autowired
    private
    FeedbackService feedbackService;

    @RequestMapping(value = "/about_page", method = RequestMethod.GET)
    public String openAboutPage(Model model) {
        model.addAttribute("records", feedbackService.getAll(10,0));
        return "about";
    }
}
