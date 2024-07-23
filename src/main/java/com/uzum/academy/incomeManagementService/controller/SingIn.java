package com.uzum.academy.incomeManagementService.controller;

import com.uzum.academy.incomeManagementService.dao.UserDAOImpl;
import com.uzum.academy.incomeManagementService.entity.User;
import com.uzum.academy.incomeManagementService.model.Client;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SingIn {
    private UserDAOImpl userDAO;

    @Autowired
    public SingIn(UserDAOImpl userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping("/sign-in")
    public String signIn(Model model) {
        model.addAttribute("client", new Client());
        return "sign-in";
    }

    @PostMapping("/processClient")
    public String processClient(
            @Valid @ModelAttribute("client") Client client,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "sign-in";
        }

        User user = userDAO.findByEmail(client.getUsername());

        if (user != null && user.getPassword().equals(client.getPassword())) {
            return "user-page";
        } else {
            return "no-email";
        }
    }
}
