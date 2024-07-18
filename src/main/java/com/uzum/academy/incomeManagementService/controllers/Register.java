package com.uzum.academy.incomeManagementService.controllers;

import com.uzum.academy.incomeManagementService.models.NewUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Register {
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("newUser", new NewUser());
        return "register";
    }

    @PostMapping("/processNewUser")
    public String processNewUser(@ModelAttribute("newUser") NewUser newUser) {
        System.out.println(newUser.getFirstName());
        System.out.println(newUser.getLastName());
        System.out.println(newUser.getEmail());
        System.out.println(newUser.getPassword());
        System.out.println(newUser.getConfirmPassword());
        return "success";
    }
}
