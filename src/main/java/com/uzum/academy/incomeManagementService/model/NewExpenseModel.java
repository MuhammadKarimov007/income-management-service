package com.uzum.academy.incomeManagementService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a model for creating a new expense entry.
 * This class contains only the essential details needed to create an expense,
 * such as the amount and a special note.
 *
 * <p>This class uses Lombok annotations to automatically generate
 * getters, setters, constructors, and other common methods.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 *     NewExpenseModel newExpense = new NewExpenseModel(150.0, "Dinner with friends");
 * </pre>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewExpenseModel {
    private Double expenseAmount;
    private String specialNote;
}
