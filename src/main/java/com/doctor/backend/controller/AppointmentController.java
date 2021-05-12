package com.doctor.backend.controller;

import com.doctor.backend.dto.AppointmentDto;
import com.doctor.backend.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PutMapping("")
    public ResponseEntity<AppointmentDto> updateAppointment( @RequestBody AppointmentDto appointmentDto) {
        return ResponseEntity.ok(this.appointmentService.updateAppointment(appointmentDto));

    }

    @GetMapping("")
    public ResponseEntity<List<AppointmentDto>> getAllAppointments() {
        return ResponseEntity.ok(this.appointmentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<AppointmentDto>> getAppointmentsByPatient(@PathVariable Long id) {
        return ResponseEntity.ok(this.appointmentService.getAppointmentsByPatient(id));
    }

    @PostMapping("/{id}")
    public ResponseEntity<AppointmentDto> addAppointment(@PathVariable Long id, @RequestBody AppointmentDto appointmentDto) {
        return ResponseEntity.ok(this.appointmentService.addNewAppointment(appointmentDto, id));
    }
}
