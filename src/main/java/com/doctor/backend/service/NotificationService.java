package com.doctor.backend.service;


import com.doctor.backend.model.Notification;

import java.util.List;

public interface NotificationService {
    List<Notification> getAllNotification();

    List<Notification> getAllNotificationByPatient(Long patientId);

    Notification getNotificationById(Long patientId);

    Boolean setNotificationAsRead(Long notificationId);

    void addNotification(Long patientId, Notification notification);

    void removeNotification(Long notificationId);

    Notification updateNotification(Notification updateNotification);

}
