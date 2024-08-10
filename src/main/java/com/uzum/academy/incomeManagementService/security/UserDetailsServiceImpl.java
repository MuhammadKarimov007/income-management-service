package com.uzum.academy.incomeManagementService.security;

import com.uzum.academy.incomeManagementService.dao.UserDao;
import com.uzum.academy.incomeManagementService.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserDao userDao;

    public UserDetailsServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user =  userDao.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Can't find the email"));
        return new UserDetailsImpl(
                user.getEmail(),
                user.getPassword()
        );
    }
}
