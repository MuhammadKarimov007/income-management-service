package com.uzum.academy.incomeManagementService.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.Date;

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
