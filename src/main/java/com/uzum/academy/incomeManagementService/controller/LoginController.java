package com.uzum.academy.incomeManagementService.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/auth/login")
    public String login() {
        return "login-view";
    }
}
