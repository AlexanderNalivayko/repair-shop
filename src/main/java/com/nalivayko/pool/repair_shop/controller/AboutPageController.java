package com.nalivayko.pool.repair_shop.controller;

import com.nalivayko.pool.repair_shop.services.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class AboutPageController {

    @Autowired
    private
    FeedbackService feedbackService;

    @RequestMapping(value = "/about_page", method = RequestMethod.GET)
    public String openAboutPage(
            HttpSession session,
            @PageableDefault(size = 5) Pageable pageable,
            Model model) {
        model.addAttribute("records", feedbackService.getAll(pageable));
        return "about";
    }
}
