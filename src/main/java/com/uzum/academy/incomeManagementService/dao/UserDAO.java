package com.uzum.academy.incomeManagementService.dao;

import com.uzum.academy.incomeManagementService.entity.User;

public interface UserDAO {
    void save(User user);
    User findById(Integer id);
    User findByEmail(String email);
}
