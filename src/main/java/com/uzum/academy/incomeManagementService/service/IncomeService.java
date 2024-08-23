package com.uzum.academy.incomeManagementService.service;

import com.uzum.academy.incomeManagementService.repository.IncomeRepo;
import com.uzum.academy.incomeManagementService.repository.UserRepo;
import com.uzum.academy.incomeManagementService.entity.IncomeEntity;
import com.uzum.academy.incomeManagementService.entity.UserEntity;
import com.uzum.academy.incomeManagementService.model.NewIncomeModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

/**
 * Service class for managing income-related operations.
 * <p>
 * The {@code IncomeService} class provides methods for saving new income entities
 * and retrieving incomes for a specific user within a given date range.
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
public class IncomeService {
    private final AuthService authService;
    private final UserRepo userRepo;
    private final IncomeRepo incomeRepo;

    /**
     * Saves a new {@link IncomeEntity} for the current authenticated user.
     * <p>
     * This method creates a new income entity based on the provided {@link NewIncomeModel},
     * associates it with the current authenticated user, and saves the user entity along with the new income.
     * </p>
     *
     * @param model the {@link NewIncomeModel} containing the data for the new income.
     */
    public void saveNewIncomeEntity(NewIncomeModel model) {
        UserEntity currentUser = authService.findCurrentUserByEmail();
        currentUser.addIncome(createIncomeEntity(model));
        userRepo.save(currentUser);
    }

    /**
     * Finds and returns a list of {@link IncomeEntity} objects for the current authenticated user
     * within the specified date range.
     * <p>
     * This method converts the start and end dates from {@code String} to {@code Date} objects and
     * retrieves the incomes for the current authenticated user within that period.
     * </p>
     *
     * @param startDate the start date of the period as a string in the format {@code yyyy-MM-dd}.
     * @param endDate   the end date of the period as a string in the format {@code yyyy-MM-dd}.
     * @return a list of {@link IncomeEntity} objects for the given period.
     */
    public List<IncomeEntity> findIncomeForGivenPeriod(String startDate, String endDate) {
        UserEntity currentUser = authService.findCurrentUserByEmail();
        Date sDate = turnStringToDate(startDate);
        Date eDate = turnStringToDate(endDate);
        return incomeRepo.findByUserIdAndIncomeDateBetween(currentUser.getId(), sDate, eDate);
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
