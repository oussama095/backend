package com.doctor.backend.controller;

import com.doctor.backend.dto.PatientDto;
import com.doctor.backend.service.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/patient")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("s")
    public List<PatientDto> all() {
        var patientsDto = new ArrayList<PatientDto>();
        patientService.getAllPatients().forEach(patient -> patientsDto.add(PatientDto.fromEntity(patient)));
        return patientsDto;
    }

    @GetMapping("")
    public PatientDto patientById(@RequestParam Long patientId) {
        return PatientDto.fromEntity(patientService.getPatientById(patientId));
    }

    @PostMapping("")
    public PatientDto patientById(@RequestBody PatientDto newPatient) {
        return PatientDto.fromEntity(patientService.addPatient(PatientDto.toEntity(newPatient)));
    }

    @PutMapping("")
    public PatientDto updatePatient(@RequestBody PatientDto updatedPatient) {
        return PatientDto.fromEntity(patientService.updatePatient(PatientDto.toEntity(updatedPatient)));
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable(value = "id") Long patientId) {
        patientService.deletePatient(patientId);
    }
}
