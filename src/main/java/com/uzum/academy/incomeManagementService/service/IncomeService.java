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

@Service
@RequiredArgsConstructor
public class IncomeService {
    private final AuthService authService;
    private final UserRepo userRepo;
    private final IncomeRepo incomeRepo;

    public void saveNewIncomeEntity(NewIncomeModel model) {
        UserEntity currentUser = authService.findCurrentUserByEmail();
        currentUser.addIncome(createIncomeEntity(model));
        userRepo.save(currentUser);
    }

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
