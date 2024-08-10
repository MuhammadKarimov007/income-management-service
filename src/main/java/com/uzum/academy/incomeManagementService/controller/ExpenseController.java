package com.uzum.academy.incomeManagementService.controller;

import com.uzum.academy.incomeManagementService.dao.UserDao;
import com.uzum.academy.incomeManagementService.entity.ExpenseEntity;
import com.uzum.academy.incomeManagementService.entity.UserEntity;
import com.uzum.academy.incomeManagementService.model.NewExpenseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalTime;
import java.util.Date;

@Controller
@RequestMapping("/app/expense")
public class ExpenseController {
    private final UserDao userDao;

    @Autowired
    public ExpenseController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping
    public String expenseAddPage(Model model) {
        model.addAttribute("newExpenseModel", new NewExpenseModel());
        return "add-expense-view";
    }

    @PostMapping
    public String processExpense(@ModelAttribute(name = "newExpenseModel") NewExpenseModel expenseModel) {
        ExpenseEntity expenseEntity = createExpenseEntity(expenseModel);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        UserEntity user = userDao.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User couldn't be found"));
        user.addExpense(expenseEntity);
        userDao.save(user);

        return "redirect:/app/main";
    }

    private ExpenseEntity createExpenseEntity(NewExpenseModel expenseModel) {
        return new ExpenseEntity(
                expenseModel.getExpenseAmount(),
                new Date(),
                LocalTime.now(),
                expenseModel.getSpecialNote()
        );
    }

}
