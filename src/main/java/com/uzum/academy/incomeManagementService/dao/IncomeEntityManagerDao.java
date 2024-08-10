package com.uzum.academy.incomeManagementService.dao;

import com.uzum.academy.incomeManagementService.entity.IncomeEntity;
import com.uzum.academy.incomeManagementService.entity.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class IncomeEntityManagerDao {

    private final EntityManager entityManager;

    @Autowired
    public IncomeEntityManagerDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public IncomeEntity findIncomeById(Long id) {
        return entityManager.find(IncomeEntity.class, id);
    }
}