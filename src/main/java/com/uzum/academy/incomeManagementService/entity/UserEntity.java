package com.uzum.academy.incomeManagementService.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user entity in the income management system.
 * This class is mapped to the "users" table in the database.
 * It includes information such as the user's first name, last name, email, password,
 * and their activation status.
 * The class also maintains relationships
 * with the user's income and expense records.
 *
 * <p>This class uses Lombok annotations to automatically generate
 * getters, setters, a no-argument constructor, and an all-argument constructor.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 *     UserEntity user = new UserEntity("John", "Doe", "john.doe@example.com", "password123");
 *     IncomeEntity income = new IncomeEntity(...);
 *     user.addIncome(income);
 *     ExpenseEntity expense = new ExpenseEntity(...);
 *     user.addExpense(expense);
 * </pre>
 *
 * @see IncomeEntity
 * @see ExpenseEntity
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "activated")
    private boolean isActivated;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<IncomeEntity> incomes = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ExpenseEntity> expenses = new ArrayList<>();

    public UserEntity(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    /**
     * Adds an income entry to the user's list of incomes.
     * The income entry is associated with this user.
     *
     * @param income the income entity to be added
     */
    public void addIncome(IncomeEntity income) {
        if (incomes == null) incomes = new ArrayList<>();
        incomes.add(income);
        income.setUser(this);
    }

    /**
     * Adds an expense entry to the user's list of expenses.
     * The expense of entry is associated with this user.
     *
     * @param expense the expense entity to be added
     */
    public void addExpense(ExpenseEntity expense) {
        if (expenses == null) expenses = new ArrayList<>();
        expenses.add(expense);
        expense.setUser(this);
    }

}
