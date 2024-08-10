package com.uzum.academy.incomeManagementService.controller;

import com.uzum.academy.incomeManagementService.dao.ExpenseDao;
import com.uzum.academy.incomeManagementService.dao.IncomeDao;
import com.uzum.academy.incomeManagementService.dao.UserDao;
import com.uzum.academy.incomeManagementService.entity.ExpenseEntity;
import com.uzum.academy.incomeManagementService.entity.IncomeEntity;
import com.uzum.academy.incomeManagementService.entity.UserEntity;
import com.uzum.academy.incomeManagementService.model.BalancePeriodModel;
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
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/app/balance")
public class BalanceForPeriodController {
    private final IncomeDao incomeDao;
    private final ExpenseDao expenseDao;
    private final UserDao userDao;

    public BalanceForPeriodController(IncomeDao incomeDao, ExpenseDao expenseDao, UserDao userDao) {
        this.incomeDao = incomeDao;
        this.expenseDao = expenseDao;
        this.userDao = userDao;
    }

    @GetMapping
    public String showBalanceForPeriodPage(Model model) {
        model.addAttribute("balanceModel", new BalancePeriodModel());
        return "balance-for-period-view";
    }

    @PostMapping
    public String processPeriodForBalance(@ModelAttribute(name = "balanceModel") BalancePeriodModel periodModel, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        UserEntity user = userDao.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User couldn't be found"));

        Date startDate = turnStringToDate(periodModel.getStartDate());
        Date endDate = turnStringToDate(periodModel.getEndDate());

        double balance = getBalance(user.getId(), startDate, endDate);
        System.out.println(balance);

        model.addAttribute("balance", String.format("%.2f", balance));

        return "balance-result-view";
    }

    private double getBalance(Long id, Date startDate, Date endDate) {
        List<IncomeEntity> incomeEntityList = getIncomes(id, startDate, endDate);
        List<ExpenseEntity> expenseEntityList = getExpenses(id, startDate, endDate);

        double balance = 0d;

        for (IncomeEntity incomeEntity : incomeEntityList) {
            balance += incomeEntity.getIncomeAmount();
        }

        for (ExpenseEntity expenseEntity : expenseEntityList) {
            balance -= expenseEntity.getExpenseAmount();
        }

        return balance;
    }

    private List<IncomeEntity> getIncomes(Long id, Date startDate, Date endDate) {
        return incomeDao.findByUserIdAndIncomeDateBetween(id, startDate, endDate);
    }

    private List<ExpenseEntity> getExpenses(Long id, Date startDate, Date endDate) {
        return expenseDao.findByUserIdAndExpenseDateBetween(id, startDate, endDate);
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
}
