package com.uzum.academy.incomeManagementService.controllers;

import com.uzum.academy.incomeManagementService.models.Client;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SingIn {
    @GetMapping("/sign-in")
    public String signIn(Model model) {
        model.addAttribute("client", new Client());
        return "sign-in";
    }

    @PostMapping("/processClient")
    public String processClient(@ModelAttribute("client") Client client) {
        System.out.println(client.getUsername());
        System.out.println(client.getPassword());
        return "success";
    }
}
