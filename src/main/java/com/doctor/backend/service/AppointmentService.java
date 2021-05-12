package com.doctor.backend.service;

import com.doctor.backend.dto.AppointmentDto;

import java.util.List;

public interface AppointmentService {

    List<AppointmentDto> getAll();

    List<AppointmentDto>  getAppointmentsByPatient(Long patientId);

    AppointmentDto addNewAppointment(AppointmentDto newAppointment, Long patientId);

    AppointmentDto updateAppointment(AppointmentDto appointment);

}
