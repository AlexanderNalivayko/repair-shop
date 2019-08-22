package com.nalivayko.pool.repair_shop.controller;

import com.nalivayko.pool.repair_shop.model.RepairRequest;
import com.nalivayko.pool.repair_shop.services.RepairRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RepairPageController {

    @Autowired
    private RepairRequestService repairRequestService;

    @RequestMapping(value = "/repair_page", method = RequestMethod.GET)
    public String openRepairPage(
            Authentication authentication,
            @PageableDefault(size = 3) Pageable pageable,
            Model model) {
        model.addAttribute("records", repairRequestService.getAllByUserName(authentication.getName(), pageable));
        model.addAttribute("repair-request", new RepairRequest());
        return "repair";
    }

    @RequestMapping(value = "/repair_page", method = RequestMethod.POST)
    public String registerUserAccount
            (@ModelAttribute("repair-request") RepairRequest repairRequest,
             BindingResult result,
             Model model) {
        RepairRequest createdRepairRequest = repairRequestService.create(user);
        if (!result.hasErrors() && createdRepairRequest != null) {
//            model.addAttribute("createdUser", createdUser.getUsername());
        } else {
//            model.addAttribute("fail", user.getUsername());
        }
        return "sign_up";
    }
}
