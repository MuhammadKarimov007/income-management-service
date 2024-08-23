package com.uzum.academy.incomeManagementService.service;

import com.uzum.academy.incomeManagementService.repository.UserRepo;
import com.uzum.academy.incomeManagementService.entity.UserEntity;
import com.uzum.academy.incomeManagementService.model.NewUserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final UserRepo userRepo;

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
