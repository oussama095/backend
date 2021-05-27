package com.doctor.backend.security.service;

import com.doctor.backend.security.model.Role;
import com.doctor.backend.security.model.User;
import com.doctor.backend.security.repository.RoleRepository;
import com.doctor.backend.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    @Autowired
    public UserServiceImp(UserRepository userRepository,
                          RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userRepository.findByEmail(email).orElseThrow(()
                -> new UsernameNotFoundException(String.format("Email %s not found", email))
        );
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());
        return new UserDetailsImp(
                user.getEmail(),
                user.getPassword(),
                3L,
                authorities
        );
    }


    @Override
    public Optional<User> registerUser(User user) {
        roleRepository.saveAll(user.getRoles());
        return Optional.of(userRepository.saveAndFlush(user));
    }

    @Override
    public Optional<User> addRoleToUser(String email, Role role) {
        return Optional.empty();
    }
}
