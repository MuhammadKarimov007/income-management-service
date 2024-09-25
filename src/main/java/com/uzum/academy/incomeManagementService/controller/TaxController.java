package com.uzum.academy.incomeManagementService.controller;

import com.uzum.academy.incomeManagementService.model.TaxInfoModel;
import com.uzum.academy.incomeManagementService.model.TaxModel;
import com.uzum.academy.incomeManagementService.service.TaxService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * A Spring MVC controller for handling tax-related requests.
 * <p>
 * The {@code TaxController} class manages requests related to tax calculations.
 * It provides endpoints to display a form for tax information input and to process the form to generate tax information based on the input.
 * </p>
 *
 * <p>
 * This controller is annotated with {@code @Controller}, {@code @RequestMapping}, and
 * {@code @RequiredArgsConstructor} from Lombok:
 * </p>
 * <ul>
 *     <li>{@code @Controller} indicates that this class is a Spring MVC controller.</li>
 *     <li>{@code @RequestMapping("/app/tax")} maps HTTP requests to "/app/tax" to this controller.</li>
 *     <li>{@code @RequiredArgsConstructor} generates a constructor with required arguments for all final fields.</li>
 * </ul>
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/app/tax")
public class TaxController {
    private final TaxService taxService;

    /**
     * Displays the form for entering tax information.
     * <p>
     * This method handles GET requests to "/app/tax" and initializes a new
     * {@code TaxModel} object to be used in the form.
     * </p>
     *
     * @param model the model to be used in the view
     * @return the name of the view to render
     */
    @GetMapping
    public String showTaxForm(Model model) {
        model.addAttribute("taxModel", new TaxModel());
        return "tax-form-view";
    }

    /**
     * Processes the submitted tax information form and generates tax info.
     * <p>
     * This method handles POST requests to "/app/tax" and uses the provided
     * {@code TaxModel} to generate tax information. The resulting list of tax information is added
     * to the model for display.
     * </p>
     *
     * @param taxModel the model containing the year and tax percentage
     * @param model the model to be used in the view
     * @return the name of the view to render
     */
    @PostMapping
    public String processTaxForm(
            @ModelAttribute(name = "taxModel") TaxModel taxModel,
            Model model
    ) {
        List<TaxInfoModel> taxInfoModel = taxService.generateTaxInfoMap(
                taxModel.getYear(),
                taxModel.getPercent()
        );
        model.addAttribute("taxInfo", taxInfoModel);
        return "tax-info-view";
    }
}
