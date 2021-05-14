package com.doctor.backend.service;


import com.doctor.backend.model.Appointment;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentService {

    List<Appointment> getAll();

    List<Appointment> getAppointmentsByPatient(Long patientId);

    Appointment addNewAppointment(Appointment newAppointment, Long patientId);

    Appointment updateAppointment(Appointment appointment);

    Appointment updateAppointmentDate(Long appointmentId, LocalDateTime start, LocalDateTime end);

    void deleteAppointment(Long appointmentId);


}
