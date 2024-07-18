package com.uzum.academy.incomeManagementService.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SingIn {
    @GetMapping("/sign-in")
    public String signIn() {
        return "sign-in";
    }
}
