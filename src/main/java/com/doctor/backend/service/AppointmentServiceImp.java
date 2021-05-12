package com.doctor.backend.service;

import com.doctor.backend.dto.AppointmentDto;
import com.doctor.backend.repository.AppointmentRepository;
import com.doctor.backend.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentServiceImp implements AppointmentService {

    private final AppointmentRepository repository;
    private final PatientRepository patientRepository;

    @Autowired
    public AppointmentServiceImp(AppointmentRepository appointmentRepository, PatientRepository patientRepository) {
        this.repository = appointmentRepository;
        this.patientRepository = patientRepository;
    }


    @Override
    public List<AppointmentDto> getAll() {
        List<AppointmentDto> appointments = new ArrayList<>();
        this.repository.findAll().forEach(appointment -> appointments.add(AppointmentDto.fromEntity(appointment)));
        return appointments;
    }

    @Override
    public List<AppointmentDto> getAppointmentsByPatient(Long patientId) {
        var optionalPatient = this.patientRepository.findById(patientId);
        if (optionalPatient.isPresent()) {
            var appointments = new ArrayList<AppointmentDto>();
            optionalPatient.get().getAppointments().forEach(appointment -> appointments.add(AppointmentDto.fromEntity(appointment)));
            return appointments;
        }
        return new ArrayList<>();
    }

    @Override
    public AppointmentDto addNewAppointment(AppointmentDto newAppointment, Long patientId) {
        var optionalPatient = this.patientRepository.findById(patientId);
        if (optionalPatient.isPresent()) {
            var patient = optionalPatient.get();
            var appointment = AppointmentDto.toEntity(newAppointment);
            appointment.setPatient(patient);
            return AppointmentDto.fromEntity(this.repository.save(appointment));
        }
        return null;
    }

    @Override
    public AppointmentDto updateAppointment(AppointmentDto updateAppointment) {
        var optionalAppointment = this.repository.findById(updateAppointment.getId());
        if (optionalAppointment.isPresent()) {
            var appointment = optionalAppointment.get();
            appointment.setTitle(updateAppointment.getTitle());
            appointment.setDescription(updateAppointment.getDescription());
            appointment.setDate(updateAppointment.getDate());
            return AppointmentDto.fromEntity(this.repository.save(appointment));
        }
        return null;
    }
}
