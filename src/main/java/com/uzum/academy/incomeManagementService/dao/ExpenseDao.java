package com.uzum.academy.incomeManagementService.dao;

import com.uzum.academy.incomeManagementService.entity.ExpenseEntity;
import com.uzum.academy.incomeManagementService.entity.IncomeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ExpenseDao extends JpaRepository<ExpenseEntity, Long> {
    List<ExpenseEntity> findByUserIdAndExpenseDateBetween(Long userId, Date startDate, Date endDate);
}
