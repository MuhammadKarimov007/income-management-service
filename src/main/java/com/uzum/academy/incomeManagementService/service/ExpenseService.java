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

/**
 * Service class for managing expense-related operations.
 * <p>
 * The {@code ExpenseService} class provides methods for saving new expense entities
 * and retrieving expenses for a specific user within a given date range.
 * </p>
 *
 * <p>
 * This class is annotated with {@code @Service}, indicating it is a service component in the Spring framework.
 * It is also annotated with {@code @RequiredArgsConstructor} from Lombok, which generates a constructor for
 * all final fields.
 * </p>
 */
@Service
@RequiredArgsConstructor
public class ExpenseService {
    private final AuthService authService;
    private final ExpenseRepo expenseRepo;
    private final UserRepo userRepo;

    /**
     * Saves a new {@link ExpenseEntity} for the current authenticated user.
     * <p>
     * This method creates a new expense entity based on the provided {@link NewExpenseModel},
     * associates it with the current authenticated user, and saves the user entity along with the new expense.
     * </p>
     *
     * @param model the {@link NewExpenseModel} containing the data for the new expense.
     */
    public void saveNewExpenseEntity(NewExpenseModel model) {
        UserEntity currentUser = authService.findCurrentUserByEmail();
        currentUser.addExpense(createExpenseEntity(model));
        userRepo.save(currentUser);
    }

    /**
     * Finds and returns a list of {@link ExpenseEntity} objects for the current authenticated user
     * within the specified date range.
     * <p>
     * This method converts the start and end dates from {@code String} to {@code Date} objects and
     * retrieves the expenses for the current authenticated user within that period.
     * </p>
     *
     * @param startDate the start date of the period as a string in the format {@code yyyy-MM-dd}.
     * @param endDate   the end date of the period as a string in the format {@code yyyy-MM-dd}.
     * @return a list of {@link ExpenseEntity} objects for the given period.
     */
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
