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

/**
 * A Spring MVC controller for handling income-related requests.
 * <p>
 * The {@code IncomeController} class manages requests related to adding and calculating income.
 * It provides endpoints to display forms for adding new income entries and for calculating income over a given period.
 * </p>
 *
 * <p>
 * This controller is annotated with {@code @Controller}, {@code @RequestMapping}, and
 * {@code @RequiredArgsConstructor} from Lombok:
 * </p>
 * <ul>
 *     <li>{@code @Controller} indicates that this class is a Spring MVC controller.</li>
 *     <li>{@code @RequestMapping("/app/income")} maps HTTP requests to "/app/income" to this controller.</li>
 *     <li>{@code @RequiredArgsConstructor} generates a constructor with required arguments for all final fields.</li>
 * </ul>
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/app/income")
public class IncomeController {
    private final IncomeService incomeService;

    /**
     * Displays the page for adding a new income entry.
     * <p>
     * This method handles GET requests to "/app/income" and initializes a new
     * {@code NewIncomeModel} object to be used in the form.
     * </p>
     *
     * @param model the model to be used in the view
     * @return the name of the view to render
     */
    @GetMapping
    public String incomeAddPage(Model model) {
        model.addAttribute("newIncomeModel", new NewIncomeModel());
        return "add-income-view";
    }

    /**
     * Processes the form submission for adding a new income entry.
     * <p>
     * This method handles POST requests to "/app/income" and saves the new income
     * using the provided {@code NewIncomeModel}.
     * </p>
     *
     * @param incomeModel the model containing the income details to be saved
     * @return a redirect to the main application page
     */
    @PostMapping
    public String processIncome(
            @ModelAttribute(name = "newIncomeModel") NewIncomeModel incomeModel
    ) {
        incomeService.saveNewIncomeEntity(incomeModel);
        return "redirect:/app/main";
    }

    /**
     * Displays the page for calculating income over a given period.
     * <p>
     * This method handles GET requests to "/app/income/calculate" and initializes a new
     * {@code IncomePeriodModel} object to be used in the form.
     * </p>
     *
     * @param model the model to be used in the view
     * @return the name of the view to render
     */
    @GetMapping("/calculate")
    public String incomeCalculatePage(Model model) {
        model.addAttribute("incomePeriodModel", new IncomePeriodModel());
        return "income-calculate-view";
    }

    /**
     * Processes the form submission to calculate income for the specified period.
     * <p>
     * This method handles POST requests to "/app/income/calculate" and retrieves
     * income entries based on the provided period. The resulting list of income entries is added
     * to the model for display.
     * </p>
     *
     * @param periodModel the model containing the start and end dates for the income calculation
     * @param model the model to be used in the view
     * @return the name of the view to render
     */
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
