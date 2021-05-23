package com.doctor.backend.security.repository;


import com.doctor.backend.security.model.ERole;
import com.doctor.backend.security.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findById(Long roleId);

    @Override
    void delete(Role role);

    Optional<Role> findByName(ERole name);

}
