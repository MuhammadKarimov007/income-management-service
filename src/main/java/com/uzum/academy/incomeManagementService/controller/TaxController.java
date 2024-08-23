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

@Controller
@RequiredArgsConstructor
@RequestMapping("/app/tax")
public class TaxController {

    private final TaxService taxService;

    @GetMapping
    public String showTaxForm(Model model) {
        model.addAttribute("taxModel", new TaxModel());
        return "tax-form-view";
    }

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
