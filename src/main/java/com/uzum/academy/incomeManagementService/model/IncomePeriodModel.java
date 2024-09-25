package com.uzum.academy.incomeManagementService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A model class representing a period for income records.
 * <p>
 * The {@code IncomePeriodModel} class is used to encapsulate the start and end dates
 * for an income period. This model is typically used to specify a time range for querying
 * or filtering income records.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncomePeriodModel {
    private String startDate;
    private String endDate;
}
