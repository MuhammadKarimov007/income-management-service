package com.uzum.academy.incomeManagementService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewUserModel {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmPassword;
}
