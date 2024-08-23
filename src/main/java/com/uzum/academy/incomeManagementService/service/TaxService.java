package com.uzum.academy.incomeManagementService.service;

import com.uzum.academy.incomeManagementService.repository.IncomeRepo;
import com.uzum.academy.incomeManagementService.entity.IncomeEntity;
import com.uzum.academy.incomeManagementService.entity.UserEntity;
import com.uzum.academy.incomeManagementService.model.TaxInfoModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Service class for generating tax information based on user income.
 * <p>
 * The {@code TaxService} class is responsible for calculating tax information
 * for a user's income within a specified year. The tax is calculated based on
 * a percentage provided by the user.
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
public class TaxService {
    private final IncomeRepo incomeRepo;
    private final AuthService authService;

    /**
     * Generates a list of {@link TaxInfoModel} objects representing tax information for the current user.
     * <p>
     * This method retrieves all income records for the current user in the specified year,
     * calculates the tax on each income using the provided percentage, and returns the results
     * as a list of {@link TaxInfoModel} objects.
     * </p>
     *
     * @param year    the year for which the tax information is to be generated.
     * @param percent the tax percentage to apply.
     * @return a list of {@link TaxInfoModel} objects containing tax information.
     */
    public List<TaxInfoModel> generateTaxInfoMap(String year, Double percent) {
        UserEntity currentUser = authService.findCurrentUserByEmail();
        List<IncomeEntity> incomeEntities = getIncomesForGivenYear(
                currentUser.getId(),
                turnStringToDate(year)
        );
        List<TaxInfoModel> taxInfoModelList = new ArrayList<>();
        for (IncomeEntity entity : incomeEntities) {
            taxInfoModelList.add(calculateTax(entity.getIncomeAmount(), percent));
        }

        return taxInfoModelList;
    }

    private TaxInfoModel calculateTax(Double totalMoney, Double taxPercent) {
        double taxedMoney = totalMoney * (taxPercent / 100);
        double remainingMoney = totalMoney - taxedMoney;

        return new TaxInfoModel(totalMoney, taxedMoney, remainingMoney);
    }

    private List<IncomeEntity> getIncomesForGivenYear(Long id, Date year) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(year);
        calendar.set(Calendar.MONTH, Calendar.DECEMBER);
        calendar.set(Calendar.DAY_OF_MONTH, 31);
        return incomeRepo.findByUserIdAndIncomeDateBetween(id,
                year, calendar.getTime()
        );
    }

    private Date turnStringToDate(String year) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        Date date = null;
        try {
            date = dateFormat.parse(year);
        } catch (ParseException ignored) {
            System.out.println("Parsing error");
        }
        return date;
    }

}
