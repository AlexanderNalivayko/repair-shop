package com.nalivayko.pool.repair_shop.controller;

import com.nalivayko.pool.repair_shop.services.RepairRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class MasterPageController {

    @Autowired
    private RepairRequestService repairRequestService;

    @RequestMapping(value = "/master", method = RequestMethod.GET)
    public String openMasterPage(
            @PageableDefault(size = 3) Pageable pageable,
            Model model) {
        model.addAttribute("records", repairRequestService.getAllAccepted(pageable));
        return "master";
    }

    @RequestMapping(value = "/master", method = RequestMethod.POST)
    public RedirectView reject(@RequestParam("repairId") Integer id) {
        repairRequestService.performRepairRequest(id);
        return new RedirectView("/master");
    }
}
