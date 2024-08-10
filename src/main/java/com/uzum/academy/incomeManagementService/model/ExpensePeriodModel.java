package com.uzum.academy.incomeManagementService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpensePeriodModel {
    private String startDate;
    private String endDate;
}
