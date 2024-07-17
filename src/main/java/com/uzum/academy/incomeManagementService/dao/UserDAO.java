package com.uzum.academy.incomeManagementService.dao;

import com.uzum.academy.incomeManagementService.entities.User;

public interface UserDAO {
    void save(User user);
    User findById(Integer id);
}
