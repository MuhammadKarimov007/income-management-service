package com.uzum.academy.incomeManagementService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a model for creating a new user.
 * This class includes the necessary fields required for user registration,
 * such as the first name, last name, email, password, and password confirmation.
 *
 * <p>This class uses Lombok annotations to automatically generate
 * getters, setters, constructors, and other common methods.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 *     NewUserModel newUser = new NewUserModel("John", "Doe", "john.doe@example.com", "password123", "password123");
 * </pre>
 */
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
