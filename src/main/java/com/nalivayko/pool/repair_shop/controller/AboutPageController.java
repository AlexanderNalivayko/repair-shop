package com.nalivayko.pool.repair_shop.controller;

import com.nalivayko.pool.repair_shop.services.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
@RequestMapping("/about_page")
public class AboutPageController {

    @Autowired
    private
    FeedbackService feedbackService;

    @RequestMapping(method = RequestMethod.GET)
    public String openAboutPage(
            @PageableDefault(size = 5) Pageable pageable,
            Model model) {
        model.addAttribute("records", feedbackService.getAll(pageable));
        return "about";
    }

    @RequestMapping(value = "/delete_feedback", method = RequestMethod.POST)
    public RedirectView deleteFeedback(@RequestParam("delete-btn") Integer id){
        feedbackService.delete(id);
        return new RedirectView("/about_page");
    }

    @RequestMapping(value = "/create_feedback", method = RequestMethod.POST)
    public RedirectView createFeedback(@RequestParam("message") String message,
                                       Principal principal){
        feedbackService.create(principal.getName(), message);
        return new RedirectView("/about_page");
    }
}
