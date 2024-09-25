package com.uzum.academy.incomeManagementService.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.Date;

/**
 * Represents an expense entity in the income management system.
 * This class is mapped to the "expenses" table in the database.
 * It includes details such as the expense amount, date, time, and a special note.
 * The class also maintains a relationship with the user who recorded the expense.
 *
 * <p>This class uses Lombok annotations to automatically generate
 * getters, setters, a no-argument constructor, and an all-argument constructor.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 *     ExpenseEntity expense = new ExpenseEntity(500.0, new Date(), LocalTime.now(), "Grocery Shopping");
 *     user.addExpense(expense);
 * </pre>
 *
 * @see UserEntity
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "expenses")
public class ExpenseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "expense_amount")
    private double expenseAmount;

    @Column(name = "expense_date")
    private Date expenseDate;

    @Column(name = "expense_time")
    private LocalTime expenseTime;

    @Column(name = "special_note")
    private String specialNote;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public ExpenseEntity(Double expenseAmount, Date date, LocalTime now, String specialNote) {
        this.expenseAmount = expenseAmount;
        this.expenseDate = date;
        this.expenseTime = now;
        this.specialNote = specialNote;
    }
}
