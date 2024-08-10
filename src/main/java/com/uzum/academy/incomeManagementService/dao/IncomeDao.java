package com.uzum.academy.incomeManagementService.dao;

import com.uzum.academy.incomeManagementService.entity.IncomeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeDao extends JpaRepository<IncomeEntity, Long> {
}
