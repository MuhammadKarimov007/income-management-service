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

/**
 * A Spring MVC controller for handling expense-related requests.
 * <p>
 * The {@code ExpenseController} class manages requests related to adding and calculating expenses.
 * It provides endpoints to display forms for adding new expenses and for calculating expenses over a given period.
 * </p>
 *
 * <p>
 * This controller is annotated with {@code @Controller}, {@code @RequestMapping}, and
 * {@code @RequiredArgsConstructor} from Lombok:
 * </p>
 * <ul>
 *     <li>{@code @Controller} indicates that this class is a Spring MVC controller.</li>
 *     <li>{@code @RequestMapping("/app/expense")} maps HTTP requests to "/app/expense" to this controller.</li>
 *     <li>{@code @RequiredArgsConstructor} generates a constructor with required arguments for all final fields.</li>
 * </ul>
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/app/expense")
public class ExpenseController {
    private final ExpenseService expenseService;

    /**
     * Displays the page for adding a new expense.
     * <p>
     * This method handles GET requests to "/app/expense" and initializes a new
     * {@code NewExpenseModel} object to be used in the form.
     * </p>
     *
     * @param model the model to be used in the view
     * @return the name of the view to render
     */
    @GetMapping
    public String expenseAddPage(Model model) {
        model.addAttribute("newExpenseModel", new NewExpenseModel());
        return "add-expense-view";
    }

    /**
     * Processes the form submission for adding a new expense.
     * <p>
     * This method handles POST requests to "/app/expense" and saves the new expense
     * using the provided {@code NewExpenseModel}.
     * </p>
     *
     * @param expenseModel the model containing the expense details to be saved
     * @return a redirect to the main application page
     */
    @PostMapping
    public String processExpense(
            @ModelAttribute(name = "newExpenseModel") NewExpenseModel expenseModel
    ) {
        expenseService.saveNewExpenseEntity(expenseModel);
        return "redirect:/app/main";
    }

    /**
     * Displays the page for calculating expenses over a given period.
     * <p>
     * This method handles GET requests to "/app/expense/calculate" and initializes a new
     * {@code ExpensePeriodModel} object to be used in the form.
     * </p>
     *
     * @param model the model to be used in the view
     * @return the name of the view to render
     */
    @GetMapping("/calculate")
    public String expenseCalculatePage(Model model) {
        model.addAttribute("expensePeriodModel", new ExpensePeriodModel());
        return "expense-calculate-view";
    }

    /**
     * Processes the form submission to calculate expenses for the specified period.
     * <p>
     * This method handles POST requests to "/app/expense/calculate" and retrieves
     * expenses based on the provided period. The resulting list of expenses is added
     * to the model for display.
     * </p>
     *
     * @param periodModel the model containing the start and end dates for the expense calculation
     * @param model the model to be used in the view
     * @return the name of the view to render
     */
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
