package com.doctor.backend.service;

import com.doctor.backend.model.Appointment;
import com.doctor.backend.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentServiceImp implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientService patientService;

    @Autowired
    public AppointmentServiceImp(AppointmentRepository appointmentRepository, PatientService patientService) {
        this.appointmentRepository = appointmentRepository;
        this.patientService = patientService;
    }


    @Override
    public List<Appointment> getAll() {
        return new ArrayList<>(appointmentRepository.findAll());
    }

    @Override
    public List<Appointment> getAppointmentsByPatient(Long patientId) {
        return patientService.getPatientById(patientId).getAppointments();
    }

    @Override
    public Appointment addNewAppointment(Appointment newAppointment, Long patientId) {
        var patient = patientService.getPatientById(patientId);
        if (patient != null) {
            newAppointment.setPatient(patient);
            return appointmentRepository.save(newAppointment);
        }
        return null;
    }

    @Override
    public Appointment updateAppointment(Long appointmentId, Appointment updateAppointment) {
        var optionalAppointment = appointmentRepository.findById(appointmentId);
        if (optionalAppointment.isPresent()) {
            var appointment = optionalAppointment.get();
            appointment.setId(appointmentId);
            appointment.setTitle(updateAppointment.getTitle());
            appointment.setDescription(updateAppointment.getDescription());
            appointment.setStart(updateAppointment.getStart());
            appointment.setEnd(updateAppointment.getEnd());
            return this.appointmentRepository.save(appointment);
        }
        return null;
    }

    @Override
    public void deleteAppointment(Long appointmentId) {
        appointmentRepository.deleteById(appointmentId);
    }

    @Override
    public Appointment updateAppointmentDate(Long appointmentId, LocalDateTime start, LocalDateTime end) {
        var optionalAppointment = appointmentRepository.findById(appointmentId);
        if (optionalAppointment.isPresent()) {
            var appointment = optionalAppointment.get();
            appointment.setId(appointmentId);
            appointment.setStart(start);
            appointment.setEnd(end);
            return this.appointmentRepository.save(appointment);
        }
        return null;
    }
}
