package com.uzum.academy.incomeManagementService.service;

import com.uzum.academy.incomeManagementService.repository.UserRepo;
import com.uzum.academy.incomeManagementService.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepo userRepo;
    public UserEntity findCurrentUserByEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepo.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User couldn't be found")
        );
    }
}
