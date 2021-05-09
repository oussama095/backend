package com.doctor.backend.repository.user;

import com.doctor.backend.model.Notification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

public interface NotificationRepository extends CrudRepository<Notification, Long> {

    Optional<Notification> findById(@RequestParam Long id);
}