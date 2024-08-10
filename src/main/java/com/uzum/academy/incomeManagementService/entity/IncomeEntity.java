package com.uzum.academy.incomeManagementService.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.Date;
/**
 * Represents an income entity in the income management system.
 * This class is mapped to the "incomes" table in the database.
 * It includes details such as the income amount, date, time, and a special note.
 * The class also maintains a relationship with the user who recorded the income.
 *
 * <p>This class uses Lombok annotations to automatically generate
 * getters, setters, a no-argument constructor, and an all-argument constructor.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 *     IncomeEntity income = new IncomeEntity(1000.0, new Date(), LocalTime.now(), "Salary for July");
 *     user.addIncome(income);
 * </pre>
 *
 * @see UserEntity
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "incomes")
public class IncomeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "income_amount")
    private double incomeAmount;

    @Column(name = "income_date")
    private Date incomeDate;

    @Column(name = "income_time")
    private LocalTime incomeTime;

    @Column(name = "special_note")
    private String specialNote;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public IncomeEntity(Double incomeAmount, Date date, LocalTime now, String specialNote) {
        this.incomeAmount = incomeAmount;
        this.incomeDate = date;
        this.incomeTime = now;
        this.specialNote = specialNote;
    }
}
