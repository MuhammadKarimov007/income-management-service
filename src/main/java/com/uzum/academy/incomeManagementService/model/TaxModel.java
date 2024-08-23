package com.uzum.academy.incomeManagementService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A model class representing tax-related information.
 * <p>
 * The {@code TaxModel} class is a simple data container for holding
 * tax-related details such as the year and the tax percentage.
 * </p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaxModel {
    private String year;
    private Double percent;
}
