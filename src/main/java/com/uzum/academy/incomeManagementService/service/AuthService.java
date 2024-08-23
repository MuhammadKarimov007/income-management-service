package com.uzum.academy.incomeManagementService.service;

import com.uzum.academy.incomeManagementService.repository.UserRepo;
import com.uzum.academy.incomeManagementService.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service class for handling authentication-related operations.
 * <p>
 * This service provides methods to retrieve the current authenticated user based on their email address.
 * </p>
 *
 * <p>
 * The {@code AuthService} class is annotated with {@code @Service}, indicating that it's a service component
 * in the Spring framework. It is also annotated with {@code @RequiredArgsConstructor} from Lombok, which
 * generates a constructor for all final fields.
 * </p>
 *
 * <p>
 * The primary method in this service is {@link #findCurrentUserByEmail()}, which fetches the currently
 * authenticated user's details from the repository using their email address.
 * </p>
 */
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepo userRepo;

    /**
     * Finds the currently authenticated user based on their email address.
     * <p>
     * This method retrieves the current authentication object from the security context, extracts the user's
     * email from the authentication principal, and then queries the {@link UserRepo} to find the corresponding
     * {@link UserEntity}. If the user is not found, a {@link UsernameNotFoundException} is thrown.
     * </p>
     *
     * @return The {@link UserEntity} of the currently authenticated user.
     * @throws UsernameNotFoundException if no user is found with the authenticated email.
     */
    public UserEntity findCurrentUserByEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepo.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User couldn't be found")
        );
    }
}
