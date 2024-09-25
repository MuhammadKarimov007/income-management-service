package com.uzum.academy.incomeManagementService.controller;

import com.uzum.academy.incomeManagementService.entity.UserEntity;
import com.uzum.academy.incomeManagementService.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/app")
public class MainController {
    private final AuthService authService;

    @GetMapping("/main")
    public String mainPage(Model model) {
        UserEntity currentUser = authService.findCurrentUserByEmail();
        model.addAttribute("incomeList", currentUser.getIncomes());
        model.addAttribute("expenseList", currentUser.getExpenses());
        return "main-view";
    }

}
