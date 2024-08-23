package com.uzum.academy.incomeManagementService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaxInfoModel {
    private Double preTaxMoney;
    private Double taxedMoney;
    private Double remainingMoney;
}
