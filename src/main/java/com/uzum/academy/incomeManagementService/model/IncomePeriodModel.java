package com.uzum.academy.incomeManagementService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncomePeriodModel {
    private String startDate;
    private String endDate;
}
