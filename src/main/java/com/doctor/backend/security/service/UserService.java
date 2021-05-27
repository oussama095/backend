package com.doctor.backend.security.service;

import com.doctor.backend.security.model.Role;
import com.doctor.backend.security.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {


    Optional<User> registerUser(User user);

    Optional<User> addRoleToUser(String email, Role role);


}
