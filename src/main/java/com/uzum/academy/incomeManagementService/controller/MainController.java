package com.uzum.academy.incomeManagementService.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/app/main")
    public String mainPage() {
        return "main-view";
    }
}
