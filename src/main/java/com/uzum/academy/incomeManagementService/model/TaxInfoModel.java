package com.uzum.academy.incomeManagementService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A model class representing detailed tax information.
 * <p>
 * The {@code TaxInfoModel} class is used to encapsulate information about
 * the tax calculation for a given amount. It includes details on the total
 * amount before tax, the amount taxed, and the remaining amount after tax.
 * </p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaxInfoModel {
    private Double preTaxMoney;
    private Double taxedMoney;
    private Double remainingMoney;
}
