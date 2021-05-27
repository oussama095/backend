package com.doctor.backend.service;

import com.doctor.backend.model.Notification;
import com.doctor.backend.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationServiceImp implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final PatientService patientService;

    @Autowired
    public NotificationServiceImp(NotificationRepository notificationRepository, PatientService patientService) {
        this.notificationRepository = notificationRepository;
        this.patientService = patientService;
    }

    @Override
    public List<Notification> getAllNotification() {
        List<Notification> notifications = new ArrayList<>();
        this.notificationRepository.findAll().forEach(notifications::add);
        return notifications;
    }

    @Override
    public List<Notification> getAllNotificationByPatient(Long patientId) {
        return patientService.getPatientById(patientId).getNotifications();

    }

    @Override
    public Notification getNotificationById(Long notificationId) {
        var notification = this.notificationRepository.findById(notificationId);
        return notification.orElse(null);
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
    public void addNotification(Long patientId, Notification notification) {
        var patient = patientService.getPatientById(patientId);
        patient.getNotifications().add(notification);
        patientService.save(patient);

    }

    @Override
    public void removeNotification(Long notificationId) {
        notificationRepository.deleteById(notificationId);
    }

    @Override
    public Notification updateNotification(Notification updateNotification) {
        var optionalNotification = notificationRepository.findById(updateNotification.getId());
        if (optionalNotification.isPresent()) {
            var notification = optionalNotification.get();
            notification.setTitle(notification.getTitle());
            notification.setMessage(notification.getMessage());
            return notificationRepository.save(notification);
        }
        return null;
    }
}
