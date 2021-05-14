package com.doctor.backend.controller;

import com.doctor.backend.dto.AppointmentDto;
import com.doctor.backend.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {
    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("all")
    public List<AppointmentDto> getAllAppointments() {
        var appointmentsDto = new ArrayList<AppointmentDto>();
        appointmentService.getAll().forEach(appointment -> appointmentsDto.add(AppointmentDto.fromEntity(appointment)));
        return appointmentsDto;
    }

    @GetMapping("/{patientId}")
    public List<AppointmentDto> getAppointmentsByPatient(@PathVariable Long patientId) {
        var appointmentsDto = new ArrayList<AppointmentDto>();
        appointmentService.getAppointmentsByPatient(patientId).forEach(appointment -> appointmentsDto.add(AppointmentDto.fromEntity(appointment)));
        return appointmentsDto;
    }

    @GetMapping("/blocker")
    public List<AppointmentDto> getAllAppointmentsBlocker(@RequestParam(required = false) Long patientId) {
        var appointmentsDto = new ArrayList<AppointmentDto>();
        appointmentService.getAll().forEach(appointment -> {
            var blocker = new AppointmentDto();
            blocker.setStart(appointment.getStart());
            blocker.setEnd(appointment.getEnd());
            blocker.setTitle("Appointment booked");
            if (appointment.getPatient().getId().equals(patientId)) {
                blocker.setTitle(appointment.getTitle());
            }
            appointmentsDto.add(blocker);
        });
        return appointmentsDto;
    }

    @PostMapping("/{patientId}")
    public AppointmentDto addAppointment(@PathVariable Long patientId, @RequestBody AppointmentDto appointmentDto) {
        return AppointmentDto.fromEntity(this.appointmentService.addNewAppointment(AppointmentDto.toEntity(appointmentDto), patientId));
    }

    @PutMapping("")
    public AppointmentDto updateAppointment(@RequestBody AppointmentDto appointmentDto) {
        return AppointmentDto.fromEntity(this.appointmentService.updateAppointment(AppointmentDto.toEntity(appointmentDto)));

    }

    @PutMapping("/{appointmentId}")
    public AppointmentDto updateAppointmentDate(@PathVariable Long appointmentId, @RequestParam LocalDateTime start, @RequestParam LocalDateTime end) {
        return AppointmentDto.fromEntity(this.appointmentService.updateAppointmentDate(appointmentId, start, end));

    }

    @DeleteMapping("{appointmentId}")
    public void deleteAppointment(@PathVariable Long appointmentId) {
        appointmentService.deleteAppointment(appointmentId);
    }
}
