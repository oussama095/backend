package com.doctor.backend.service;

import com.doctor.backend.dto.NotificationDto;
import com.doctor.backend.repository.NotificationRepository;
import com.doctor.backend.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationServiceImp implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public NotificationServiceImp(NotificationRepository notificationRepository, PatientRepository patientRepository) {
        this.notificationRepository = notificationRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public List<NotificationDto> getAllNotification() {
        List<NotificationDto> notifications = new ArrayList<>();
        this.notificationRepository.findAll().forEach(notification -> notifications.add(NotificationDto.fromEntity(notification)));
        return notifications;
    }

    @Override
    public List<NotificationDto> getAllNotificationByPatient(Long patientId) {
        var opt = this.patientRepository.findById(patientId);
        if (opt.isPresent()) {
            List<NotificationDto> notifications = new ArrayList<>();
            opt.get().getNotifications().forEach(notification -> notifications.add(NotificationDto.fromEntity(notification)));
            return notifications;
        } else return new ArrayList<>();
    }

    @Override
    public NotificationDto getNotificationById(Long notificationId) {
        var opt = this.notificationRepository.findById(notificationId);
        if (opt.isPresent()) return NotificationDto.fromEntity(opt.get());
        return null;
    }

    @Override
    public Boolean setNotificationAsRead(Long notificationId) {
        var opt = this.notificationRepository.findById(notificationId);
        if (opt.isPresent()) {
            var notification = opt.get();
            notification.setIsRead(true);
            notificationRepository.save(notification);
            return true;
        }
        return false;
    }

    @Override
    public NotificationDto addNotification(Long patientId) {
        return null;
    }

    @Override
    public Boolean RemoveNotification(Long notificationId) {
        return null;
    }

    @Override
    public NotificationDto updateNotification(Long notificationId) {
        return null;
    }
}
