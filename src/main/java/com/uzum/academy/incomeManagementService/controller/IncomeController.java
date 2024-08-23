package com.uzum.academy.incomeManagementService.controller;

import com.uzum.academy.incomeManagementService.entity.IncomeEntity;
import com.uzum.academy.incomeManagementService.model.IncomePeriodModel;
import com.uzum.academy.incomeManagementService.model.NewIncomeModel;
import com.uzum.academy.incomeManagementService.service.IncomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/app/income")
public class IncomeController {
    private final IncomeService incomeService;

    @GetMapping
    public String incomeAddPage(Model model) {
        model.addAttribute("newIncomeModel", new NewIncomeModel());
        return "add-income-view";
    }

    @PostMapping
    public String processIncome(
            @ModelAttribute(name = "newIncomeModel") NewIncomeModel incomeModel
    ) {
        incomeService.saveNewIncomeEntity(incomeModel);
        return "redirect:/app/main";
    }

    @GetMapping("/calculate")
    public String incomeCalculatePage(Model model) {
        model.addAttribute("incomePeriodModel", new IncomePeriodModel());
        return "income-calculate-view";
    }

    @PostMapping("/calculate")
    public String processIncomePeriod(
            @ModelAttribute("incomePeriodModel") IncomePeriodModel periodModel,
            Model model
    ) {
        List<IncomeEntity> list = incomeService
                .findIncomeForGivenPeriod(periodModel.getStartDate(), periodModel.getEndDate());
        model.addAttribute("incomePeriodList", list);
        return "income-period-view";
    }
}
