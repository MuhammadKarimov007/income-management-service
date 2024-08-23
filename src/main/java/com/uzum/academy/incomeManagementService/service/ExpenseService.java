package com.uzum.academy.incomeManagementService.service;

import com.uzum.academy.incomeManagementService.repository.ExpenseRepo;
import com.uzum.academy.incomeManagementService.repository.UserRepo;
import com.uzum.academy.incomeManagementService.entity.ExpenseEntity;
import com.uzum.academy.incomeManagementService.entity.UserEntity;
import com.uzum.academy.incomeManagementService.model.NewExpenseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {
    private final AuthService authService;
    private final ExpenseRepo expenseRepo;
    private final UserRepo userRepo;

    public void saveNewExpenseEntity(NewExpenseModel model) {
        UserEntity currentUser = authService.findCurrentUserByEmail();
        currentUser.addExpense(createExpenseEntity(model));
        userRepo.save(currentUser);
    }

    public List<ExpenseEntity> findExpenseForGivenPeriod(String startDate, String endDate) {
        UserEntity currentUser = authService.findCurrentUserByEmail();
        Date sDate = turnStringToDate(startDate);
        Date eDate = turnStringToDate(endDate);
        return expenseRepo.findByUserIdAndExpenseDateBetween(currentUser.getId(), sDate, eDate);
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

    private ExpenseEntity createExpenseEntity(NewExpenseModel model) {
        return new ExpenseEntity(
                model.getExpenseAmount(),
                new Date(),
                LocalTime.now(),
                model.getSpecialNote()
        );
    }
}
