package com.uzum.academy.incomeManagementService.repository;

import com.uzum.academy.incomeManagementService.entity.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ExpenseRepo extends JpaRepository<ExpenseEntity, Long> {
    List<ExpenseEntity> findByUserIdAndExpenseDateBetween(Long userId, Date startDate, Date endDate);
}
