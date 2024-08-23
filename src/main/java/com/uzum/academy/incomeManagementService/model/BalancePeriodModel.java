package com.uzum.academy.incomeManagementService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A model class representing a period for balance calculations.
 * <p>
 * The {@code BalancePeriodModel} class is used to encapsulate the start and end dates
 * for a balance calculation period. This model is typically used to specify a time range
 * for computing or querying balance records.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BalancePeriodModel {
    private String startDate;
    private String endDate;
}
