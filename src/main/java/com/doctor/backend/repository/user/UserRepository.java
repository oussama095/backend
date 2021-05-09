package com.doctor.backend.repository.user;

import com.doctor.backend.model.user.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
