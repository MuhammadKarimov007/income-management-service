package com.uzum.academy.incomeManagementService.service;

import com.uzum.academy.incomeManagementService.repository.UserRepo;
import com.uzum.academy.incomeManagementService.entity.UserEntity;
import com.uzum.academy.incomeManagementService.model.NewUserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for user registration operations.
 * <p>
 * The {@code RegistrationService} class provides functionality to save a new user entity
 * to the database by mapping the provided {@link NewUserModel} to a {@link UserEntity}.
 * </p>
 *
 * <p>
 * This class is annotated with {@code @Service}, indicating it is a service component in the Spring framework.
 * It is also annotated with {@code @RequiredArgsConstructor} from Lombok, which generates a constructor for
 * all final fields.
 * </p>
 */
@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final UserRepo userRepo;
    /**
     * Saves a new user to the database.
     * <p>
     * This method maps the provided {@link NewUserModel} to a {@link UserEntity} and saves it to the database.
     * The password is prefixed with "{noop}" to indicate that it should be stored without encoding.
     * </p>
     *
     * @param newUserModel the {@link NewUserModel} containing the data for the new user.
     */
    public void saveNewUser(NewUserModel newUserModel) {
        userRepo.save(mapModelToUser(newUserModel));
    }

    private UserEntity mapModelToUser(NewUserModel model) {
        return new UserEntity(
                model.getFirstName(),
                model.getLastName(),
                model.getEmail(),
                "{noop}"+model.getPassword()
        );
    }
}
