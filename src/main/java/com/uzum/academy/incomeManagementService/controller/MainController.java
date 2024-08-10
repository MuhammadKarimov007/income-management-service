package com.uzum.academy.incomeManagementService.controller;

import com.uzum.academy.incomeManagementService.dao.UserDao;
import com.uzum.academy.incomeManagementService.entity.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app")
public class MainController {
    private final UserDao userDao;

    public MainController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/main")
    public String mainPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        UserEntity user = userDao.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User couldn't be found"));

        model.addAttribute("incomeList", user.getIncomes());
        model.addAttribute("expenseList", user.getExpenses());

        return "main-view";
    }

}
