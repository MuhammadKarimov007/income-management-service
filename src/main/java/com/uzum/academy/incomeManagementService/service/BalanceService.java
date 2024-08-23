package com.uzum.academy.incomeManagementService.service;

import com.uzum.academy.incomeManagementService.repository.ExpenseRepo;
import com.uzum.academy.incomeManagementService.repository.IncomeRepo;
import com.uzum.academy.incomeManagementService.entity.ExpenseEntity;
import com.uzum.academy.incomeManagementService.entity.IncomeEntity;
import com.uzum.academy.incomeManagementService.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BalanceService {
    private final AuthService authService;
    private final IncomeRepo incomeRepo;
    private final ExpenseRepo expenseRepo;

    public Double getBalance(String startDate, String endDate) {
        UserEntity currentUser = authService.findCurrentUserByEmail();
        Date sDate = turnStringToDate(startDate);
        Date eDate = turnStringToDate(endDate);

        List<IncomeEntity> incomeEntityList = getIncomes(currentUser.getId(),
                sDate,
                eDate
        );
        List<ExpenseEntity> expenseEntityList = getExpenses(currentUser.getId(),
                sDate,
                eDate
        );

        double balance = 0d;

        for (IncomeEntity incomeEntity : incomeEntityList) {
            balance += incomeEntity.getIncomeAmount();
        }

        for (ExpenseEntity expenseEntity : expenseEntityList) {
            balance -= expenseEntity.getExpenseAmount();
        }

        return balance;
    }

    private List<IncomeEntity> getIncomes(Long id, Date startDate,
                                          Date endDate
    ) {
        return incomeRepo.findByUserIdAndIncomeDateBetween(
                id,
                startDate,
                endDate
        );
    }

    private List<ExpenseEntity> getExpenses(Long id, Date startDate,
                                            Date endDate) {
        return expenseRepo.findByUserIdAndExpenseDateBetween(id,
                startDate,
                endDate
        );
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
