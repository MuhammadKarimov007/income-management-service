package com.uzum.academy.incomeManagementService.controller;

import com.uzum.academy.incomeManagementService.dao.UserDAOImpl;
import com.uzum.academy.incomeManagementService.entity.User;
import com.uzum.academy.incomeManagementService.model.NewUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Register {

    private UserDAOImpl userDAO;

    @Autowired
    public Register(UserDAOImpl userDAO) {
        this.userDAO = userDAO;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("newUser", new NewUser());
        return "register";
    }

    @PostMapping("/processNewUser")
    public String processNewUser(
            @Valid @ModelAttribute("newUser") NewUser newUser,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "register";
        }

        User user = new User();
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setEmail(newUser.getEmail());
        user.setPassword(newUser.getPassword());

        userDAO.save(user);

        return "success";
    }

    public boolean isConfirmPasswordIdentical(String pas1, String pas2) {
        return pas1.equals(pas2);
    }
}
