package com.uzum.academy.incomeManagementService.controller;

import com.uzum.academy.incomeManagementService.dao.IncomeDao;
import com.uzum.academy.incomeManagementService.dao.UserDao;
import com.uzum.academy.incomeManagementService.entity.IncomeEntity;
import com.uzum.academy.incomeManagementService.entity.UserEntity;
import com.uzum.academy.incomeManagementService.model.IncomePeriodModel;
import com.uzum.academy.incomeManagementService.model.NewIncomeModel;
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
@RequestMapping("/app/income")
public class IncomeController {
    private final UserDao userDao;
    private final IncomeDao incomeDao;

    @Autowired
    public IncomeController(UserDao userDao, IncomeDao incomeDao) {
        this.userDao = userDao;
        this.incomeDao = incomeDao;
    }

    @GetMapping
    public String incomeAddPage(Model model) {
        model.addAttribute("newIncomeModel", new NewIncomeModel());
        return "add-income-view";
    }

    @PostMapping
    public String processIncome(@ModelAttribute(name = "newIncomeModel") NewIncomeModel incomeModel) {
        IncomeEntity incomeEntity = createIncomeEntity(incomeModel);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        UserEntity user = userDao.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User couldn't be found"));
        user.addIncome(incomeEntity);
        userDao.save(user);

        return "redirect:/app/main";
    }

    @GetMapping("/calculate")
    public String incomeCalculatePage(Model model) {
        model.addAttribute("incomePeriodModel", new IncomePeriodModel());

        return "income-calculate-view";
    }

    @PostMapping("/calculate")
    public String processIncomePeriod(@ModelAttribute("incomePeriodModel") IncomePeriodModel periodModel, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        UserEntity user = userDao.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User couldn't be found"));

        Date startDate = turnStringToDate(periodModel.getStartDate());
        Date endDate = turnStringToDate(periodModel.getEndDate());

        List<IncomeEntity> list = incomeDao.findByUserIdAndIncomeDateBetween(user.getId(), startDate, endDate);
        model.addAttribute("incomePeriodList", list);

        return "income-period-view";
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

    private IncomeEntity createIncomeEntity(NewIncomeModel incomeModel) {
        return new IncomeEntity(
                incomeModel.getIncomeAmount(),
                new Date(),
                LocalTime.now(),
                incomeModel.getSpecialNote()
        );
    }
}
