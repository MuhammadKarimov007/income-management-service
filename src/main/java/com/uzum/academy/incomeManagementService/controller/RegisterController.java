package com.uzum.academy.incomeManagementService.controller;

import com.uzum.academy.incomeManagementService.model.NewUserModel;
import com.uzum.academy.incomeManagementService.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class RegisterController {
    private final RegistrationService registrationService;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("newUserModel", new NewUserModel());
        return "register-view";
    }

    @PostMapping("/register")
    public String processNewUser(
            @ModelAttribute("newUserModel") NewUserModel userModel
    ) {
        registrationService.saveNewUser(userModel);
        return "redirect:/auth/login";
    }
}
