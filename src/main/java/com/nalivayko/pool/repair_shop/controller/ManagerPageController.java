package com.nalivayko.pool.repair_shop.controller;

import com.nalivayko.pool.repair_shop.services.RepairRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ManagerPageController {

    @Autowired
    private RepairRequestService repairRequestService;

    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public String openManagerPage(
            @PageableDefault(size = 3) Pageable pageable,
            Model model) {
        model.addAttribute("records", repairRequestService.getAllNew(pageable));
        return "manager";
    }
}
