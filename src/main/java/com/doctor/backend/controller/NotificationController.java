package com.doctor.backend.controller;

import com.doctor.backend.dto.NotificationDto;
import com.doctor.backend.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/notification")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("")
    public NotificationDto findNotificationById(@RequestParam Long id) {
        return NotificationDto.fromEntity(this.notificationService.getNotificationById(id));
    }

    @GetMapping("/{patientId}")
    public List<NotificationDto> findNotificationByPatient(@PathVariable(value = "patientId") Long id) {
        return NotificationDto.fromEntities(this.notificationService.getAllNotificationByPatient(id));
    }

    @PutMapping("{id}")
    public Boolean setReadNotification(@PathVariable(value = "id") Long id) {
        return this.notificationService.setNotificationAsRead(id);
    }

    @DeleteMapping("/{id}")
    public void deleteNotification(@PathVariable(value = "id") Long id) {
        this.notificationService.removeNotification(id);
    }
}
