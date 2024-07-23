package com.uzum.academy.incomeManagementService.entity;

import jakarta.persistence.*;

import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "incomes")
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "income_amount")
    private Double incomeAmount;

    @Column(name = "income_date")
    private Date incomeDate;

    @Column(name = "income_time")
    private Time incomeTime;

    @Column(name = "note")
    private String note;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Income() {
    }

    public Income(Double incomeAmount, Date incomeDate, Time incomeTime, String note) {
        this.incomeAmount = incomeAmount;
        this.incomeDate = incomeDate;
        this.incomeTime = incomeTime;
        this.note = note;
    }

    public Double getIncomeAmount() {
        return incomeAmount;
    }

    public void setIncomeAmount(Double incomeAmount) {
        this.incomeAmount = incomeAmount;
    }

    public Date getIncomeDate() {
        return incomeDate;
    }

    public void setIncomeDate(Date incomeDate) {
        this.incomeDate = incomeDate;
    }

    public Time getIncomeTime() {
        return incomeTime;
    }

    public void setIncomeTime(Time incomeTime) {
        this.incomeTime = incomeTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Income{" +
                "id=" + id +
                ", incomeAmount=" + incomeAmount +
                ", incomeDate=" + incomeDate +
                ", incomeTime=" + incomeTime +
                ", note='" + note + '\'' +
                '}';
    }
}
