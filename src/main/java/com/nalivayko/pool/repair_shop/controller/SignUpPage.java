package com.nalivayko.pool.repair_shop.controller;

import com.nalivayko.pool.repair_shop.model.User;
import com.nalivayko.pool.repair_shop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SignUpPage {

    @Autowired
    private
    UserService userService;

    @RequestMapping(value = "/sign_up", method = RequestMethod.GET)
    public String openSignUpPage(Model model) {
        model.addAttribute("user", new User());
        return "sign_up";
    }

    @RequestMapping(value = "/sign_up", method = RequestMethod.POST)
    public String registerUserAccount
            (@ModelAttribute("user") User user,
             BindingResult result,
             Model model) {
        User createdUser = userService.create(user);
        if (!result.hasErrors() && createdUser != null) {
            model.addAttribute("createdUser", createdUser.getUsername());
        } else {
            model.addAttribute("fail", user.getUsername());
        }
        return "sign_up";
    }
}
