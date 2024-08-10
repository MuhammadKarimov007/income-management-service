package com.uzum.academy.incomeManagementService.controller;

import com.uzum.academy.incomeManagementService.dao.ExpenseDao;
import com.uzum.academy.incomeManagementService.dao.UserDao;
import com.uzum.academy.incomeManagementService.entity.ExpenseEntity;
import com.uzum.academy.incomeManagementService.entity.UserEntity;
import com.uzum.academy.incomeManagementService.model.ExpensePeriodModel;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/app/expense")
public class ExpenseController {
    private final UserDao userDao;
    private final ExpenseDao expenseDao;

    @Autowired
    public ExpenseController(UserDao userDao, ExpenseDao expenseDao) {
        this.userDao = userDao;
        this.expenseDao = expenseDao;
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

    @GetMapping("/calculate")
    public String expenseCalculatePage(Model model) {
        model.addAttribute("expensePeriodModel", new ExpensePeriodModel());
        return "expense-calculate-view";
    }

    @PostMapping("/calculate")
    public String processExpensePeriod(@ModelAttribute(name = "expensePeriodModel") ExpensePeriodModel periodModel, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        UserEntity user = userDao.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User couldn't be found"));

        Date startDate = turnStringToDate(periodModel.getStartDate());
        Date endDate = turnStringToDate(periodModel.getEndDate());

        List<ExpenseEntity> list = expenseDao.findByUserIdAndExpenseDateBetween(user.getId(), startDate, endDate);
        model.addAttribute("expensePeriodList", list);

        // todo - create the view
        return "expense-period-view";
    }

    private Date turnStringToDate(String stringDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = dateFormat.parse(stringDate);
        } catch (ParseException ignored) {
            System.out.println("Parsing error");
        }

        return date;
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
