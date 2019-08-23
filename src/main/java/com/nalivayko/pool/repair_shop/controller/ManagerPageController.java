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
@RequestMapping("/manager")
public class ManagerPageController {

    @Autowired
    private RepairRequestService repairRequestService;

    @RequestMapping(method = RequestMethod.GET)
    public String openManagerPage(
            @PageableDefault(size = 3) Pageable pageable,
            Model model) {
        model.addAttribute("records", repairRequestService.getAllNew(pageable));
        return "manager";
    }

    @RequestMapping(value = "/accept", method = RequestMethod.POST)
    public RedirectView accept(@RequestParam("repairId") Integer id,
                         @RequestParam("price") String price) {
        repairRequestService.acceptRepairRequest(id, price);
        return new RedirectView("/manager");

    }

    @RequestMapping(value = "/reject", method = RequestMethod.POST)
    public RedirectView reject(@RequestParam("repairId") Integer id,
                         @RequestParam("reason") String reason) {
        repairRequestService.rejectRepairRequest(id, reason);
        return new RedirectView("/manager");
    }
}
