package com.doctor.backend.controller;

import com.doctor.backend.dto.NotificationDto;
import com.doctor.backend.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("")
    public List<NotificationDto> all() {
        return this.notificationService.getAllNotification();
    }

    @GetMapping("/notification")
    public ResponseEntity<NotificationDto> findNotificationById(@RequestParam Long id) {
        return ResponseEntity.ok(this.notificationService.getNotificationById(id));
    }

    @GetMapping("/notification/{patientId}")
    public ResponseEntity<List<NotificationDto>> findNotificationByPatient(@PathVariable(value = "patientId") Long id) {
        return ResponseEntity.ok(this.notificationService.getAllNotificationByPatient(id));
    }

    @PutMapping("/notification/{id}")
    public ResponseEntity<Boolean> setReadNotification(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(this.notificationService.setNotificationAsRead(id));
    }
}
