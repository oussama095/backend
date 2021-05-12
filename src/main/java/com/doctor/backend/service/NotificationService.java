package com.doctor.backend.service;

import com.doctor.backend.dto.NotificationDto;

import java.util.List;

public interface NotificationService {
    List<NotificationDto> getAllNotification();

    List<NotificationDto> getAllNotificationByPatient(Long patientId);

    NotificationDto getNotificationById(Long patientId);

    Boolean setNotificationAsRead(Long notificationId);

    NotificationDto addNotification(Long patientId);

    Boolean RemoveNotification(Long notificationId);

    NotificationDto updateNotification(Long notificationId);

}
