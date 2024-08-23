package com.uzum.academy.incomeManagementService.repository;

import com.uzum.academy.incomeManagementService.entity.IncomeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface IncomeRepo extends JpaRepository<IncomeEntity, Long> {
    List<IncomeEntity> findByUserIdAndIncomeDateBetween(Long userId, Date startDate, Date endDate);
}
