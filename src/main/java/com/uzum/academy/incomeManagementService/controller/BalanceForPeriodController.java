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

/**
 * A Spring MVC controller for handling balance queries for a specified period.
 * <p>
 * The {@code BalanceForPeriodController} class manages requests related to balance calculations
 * for a given time period. It provides endpoints to display a form for entering the period and
 * to process the form submission to calculate the balance.
 * </p>
 *
 * <p>
 * This controller is annotated with {@code @Controller}, {@code @RequestMapping}, and
 * {@code @RequiredArgsConstructor} from Lombok:
 * </p>
 * <ul>
 *     <li>{@code @Controller} indicates that this class is a Spring MVC controller.</li>
 *     <li>{@code @RequestMapping("/app/balance")} maps HTTP requests to "/app/balance" to this controller.</li>
 *     <li>{@code @RequiredArgsConstructor} generates a constructor with required arguments for all final fields.</li>
 * </ul>
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/app/balance")
public class BalanceForPeriodController {
    private final BalanceService balanceService;

    /**
     * Displays the balance form page.
     * <p>
     * This method handles GET requests to "/app/balance" and initializes a new
     * {@code BalancePeriodModel} object to be used in the form.
     * </p>
     *
     * @param model the model to be used in the view
     * @return the name of the view to render
     */
    @GetMapping
    public String showBalanceForPeriodPage(Model model) {
        model.addAttribute("balanceModel", new BalancePeriodModel());
        return "balance-for-period-view";
    }

    /**
     * Processes the form submission to calculate the balance for the specified period.
     * <p>
     * This method handles POST requests to "/app/balance" and calculates the balance
     * based on the provided start and end dates. The result is added to the model for display.
     * </p>
     *
     * @param periodModel the model containing the start and end dates for the balance calculation
     * @param model the model to be used in the view
     * @return the name of the view to render
     */
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
