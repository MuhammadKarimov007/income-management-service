package com.uzum.academy.incomeManagementService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A model class representing a period for expense records.
 * <p>
 * The {@code ExpensePeriodModel} class is used to encapsulate the start and end dates
 * for an expense period. This model is typically used to specify a time range for querying
 * or filtering expense records.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpensePeriodModel {
    private String startDate;
    private String endDate;
}
