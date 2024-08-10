package com.uzum.academy.incomeManagementService.controller;

import com.uzum.academy.incomeManagementService.dao.UserDao;
import com.uzum.academy.incomeManagementService.entity.UserEntity;
import com.uzum.academy.incomeManagementService.model.NewUserModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class RegisterController {
    private final UserDao userDao;

    public RegisterController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("newUserModel", new NewUserModel());
        return "register-view";
    }

    @PostMapping("/register")
    public String processNewUser(
            @ModelAttribute("newUserModel") NewUserModel userModel
    ) {
        System.out.println(userModel);
        userDao.save(mapModelToUser(userModel));
        // todo - customize the default login url to /auth/login
        return "redirect:/auth/login";
    }

    private UserEntity mapModelToUser(NewUserModel model) {
        return new UserEntity(
                model.getFirstName(),
                model.getLastName(),
                model.getEmail(),
                "{noop}"+model.getPassword()
        );
    }
}
