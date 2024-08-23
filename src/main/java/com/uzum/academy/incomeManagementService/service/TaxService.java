package com.uzum.academy.incomeManagementService.service;

import com.uzum.academy.incomeManagementService.dao.IncomeDao;
import com.uzum.academy.incomeManagementService.entity.IncomeEntity;
import com.uzum.academy.incomeManagementService.entity.UserEntity;
import com.uzum.academy.incomeManagementService.model.TaxInfoModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TaxService {
    private final IncomeDao incomeDao;
    private final AuthService authService;

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
        return incomeDao.findByUserIdAndIncomeDateBetween(id,
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
