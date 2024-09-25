package com.uzum.academy.incomeManagementService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a model for creating a new income entry.
 * This class contains only the essential details needed to create an income,
 * such as the amount and a special note.
 *
 * <p>This class uses Lombok annotations to automatically generate
 * getters, setters, constructors, and other common methods.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 *     NewIncomeModel newIncome = new NewIncomeModel(2000.0, "Freelance project payment");
 * </pre>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewIncomeModel {
    private Double incomeAmount;
    private String specialNote;
}
