package com.doctor.backend.controller;

import com.doctor.backend.dto.NotificationDto;
import com.doctor.backend.exception.ResourceNotFoundException;
import com.doctor.backend.repository.user.NotificationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationRepository notificationRepository;

    public NotificationController(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @GetMapping("")
    public List<NotificationDto> all() {
        List<NotificationDto> notifications = new ArrayList<>();
        notificationRepository.findAll().forEach(notification -> notifications.add(NotificationDto.fromEntity(notification)));
        return notifications;
    }

    @GetMapping("/notification")
    public ResponseEntity<NotificationDto> findNotificationByID(@RequestParam long id) throws ResourceNotFoundException {
        var notificationDto = NotificationDto.fromEntity(notificationRepository
                .findById(id).orElseThrow(() -> new ResourceNotFoundException("notification not found")));
        return ResponseEntity.ok(notificationDto);
    }

    @PutMapping("/notification/{id}")
    public ResponseEntity<NotificationDto> setReadNotification(
            @PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {

        var notificationDto = NotificationDto.fromEntity(notificationRepository
                .findById(id).orElseThrow(() -> new ResourceNotFoundException("notification not found")));

        notificationDto.setIsRead(true);
        notificationRepository.save(NotificationDto.toEntity(notificationDto));
        return ResponseEntity.ok(notificationDto);
    }
}
