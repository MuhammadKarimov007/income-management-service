package com.uzum.academy.incomeManagementService.controller;

import com.uzum.academy.incomeManagementService.entity.ExpenseEntity;
import com.uzum.academy.incomeManagementService.model.ExpensePeriodModel;
import com.uzum.academy.incomeManagementService.model.NewExpenseModel;
import com.uzum.academy.incomeManagementService.service.ExpenseService;
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
@RequestMapping("/app/expense")
public class ExpenseController {
    private final ExpenseService expenseService;

    @GetMapping
    public String expenseAddPage(Model model) {
        model.addAttribute("newExpenseModel", new NewExpenseModel());
        return "add-expense-view";
    }

    @PostMapping
    public String processExpense(
            @ModelAttribute(name = "newExpenseModel") NewExpenseModel expenseModel
    ) {
        expenseService.saveNewExpenseEntity(expenseModel);
        return "redirect:/app/main";
    }

    @GetMapping("/calculate")
    public String expenseCalculatePage(Model model) {
        model.addAttribute("expensePeriodModel", new ExpensePeriodModel());
        return "expense-calculate-view";
    }

    @PostMapping("/calculate")
    public String processExpensePeriod(
            @ModelAttribute(name = "expensePeriodModel") ExpensePeriodModel periodModel,
            Model model
    ) {
        List<ExpenseEntity> list = expenseService
                .findExpenseForGivenPeriod(periodModel.getStartDate(),
                        periodModel.getEndDate()
                );
        model.addAttribute("expensePeriodList", list);
        return "expense-period-view";
    }
}
