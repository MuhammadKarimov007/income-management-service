package com.uzum.academy.incomeManagementService.controller;

import com.uzum.academy.incomeManagementService.dao.IncomeDao;
import com.uzum.academy.incomeManagementService.dao.IncomeEntityManagerDao;
import com.uzum.academy.incomeManagementService.dao.UserDao;
import com.uzum.academy.incomeManagementService.entity.IncomeEntity;
import com.uzum.academy.incomeManagementService.entity.UserEntity;
import com.uzum.academy.incomeManagementService.model.NewIncomeModel;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {
    private final UserDao userDao;
    private final IncomeEntityManagerDao incomeEntityManagerDao;
    private final IncomeDao incomeDao;

    public MainController(UserDao userDao, IncomeEntityManagerDao incomeEntityManagerDao, IncomeDao incomeDao) {
        this.userDao = userDao;
        this.incomeEntityManagerDao = incomeEntityManagerDao;
        this.incomeDao = incomeDao;
    }

    @GetMapping("/app/main")
    public String mainPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        UserEntity user = userDao.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User couldn't be found"));

        model.addAttribute("incomeList", user.getIncomes());

        return "main-view";
    }

    @GetMapping("/app/income")
    public String incomeAddPage(Model model) {
        model.addAttribute("newIncomeModel", new NewIncomeModel());
        return "add-income-view";
    }

    @PostMapping("/app/income")
    public String processIncome(@ModelAttribute(name = "newIncomeModel") NewIncomeModel incomeModel) {
        IncomeEntity incomeEntity = createIncomeEntity(incomeModel);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        UserEntity user = userDao.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User couldn't be found"));
        user.addIncome(incomeEntity);
        userDao.save(user);

        return "redirect:/app/main";
    }

    private IncomeEntity createIncomeEntity(NewIncomeModel incomeModel) {
        return new IncomeEntity(
                incomeModel.getIncomeAmount(),
                new Date(),
                LocalTime.now(),
                incomeModel.getSpecialNote()
        );
    }

    private List<IncomeEntity> getIncomes(Long id ) {
        Optional<UserEntity> entity = userDao.findById(id);
        System.out.println(entity.get().getIncomes());
        return entity.get().getIncomes();
    }
}
