package com.uzum.academy.incomeManagementService.controller;

import com.uzum.academy.incomeManagementService.model.BalancePeriodModel;
import com.uzum.academy.incomeManagementService.service.BalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/app/balance")
public class BalanceForPeriodController {
    private final BalanceService balanceService;

    @GetMapping
    public String showBalanceForPeriodPage(Model model) {
        model.addAttribute("balanceModel", new BalancePeriodModel());
        return "balance-for-period-view";
    }

    @PostMapping
    public String processPeriodForBalance(
            @ModelAttribute(name = "balanceModel") BalancePeriodModel periodModel,
            Model model
    ) {
        double balance = balanceService.getBalance(
                periodModel.getStartDate(),
                periodModel.getEndDate()
        );
        model.addAttribute("balance", String.format("%.2f", balance));
        return "balance-result-view";
    }
}
