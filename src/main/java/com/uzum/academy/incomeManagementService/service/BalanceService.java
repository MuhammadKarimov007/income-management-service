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

/**
 * Service class for calculating the financial balance of a user within a specified date range.
 * <p>
 * The {@code BalanceService} class is responsible for retrieving the income and expense data for the
 * authenticated user and calculating their balance over a specified time period. The balance is determined by
 * summing the user's income and subtracting their expenses.
 * </p>
 *
 * <p>
 * This class is annotated with {@code @Service}, indicating it is a service component in the Spring framework.
 * It is also annotated with {@code @RequiredArgsConstructor} from Lombok, which generates a constructor for
 * all final fields.
 * </p>
 *
 * <p>
 * The primary method is {@link #getBalance(String, String)}, which calculates the user's balance between
 * the given start and end dates.
 * </p>
 */
@Service
@RequiredArgsConstructor
public class BalanceService {
    private final AuthService authService;
    private final IncomeRepo incomeRepo;
    private final ExpenseRepo expenseRepo;

    /**
     * Calculates the financial balance for the current authenticated user within a specified date range.
     * <p>
     * This method converts the provided start and end dates from {@code String} to {@code Date} objects,
     * retrieves the user's income and expense data for that period, and calculates the balance by summing
     * the income and subtracting the expenses.
     * </p>
     *
     * @param startDate the start date of the period as a string in the format {@code yyyy-MM-dd}.
     * @param endDate   the end date of the period as a string in the format {@code yyyy-MM-dd}.
     * @return the calculated balance as a {@code Double}.
     */
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
