package com.doctor.backend.controller;

import com.doctor.backend.dto.AddressDto;
import com.doctor.backend.dto.PatientDto;
import com.doctor.backend.exception.ResourceNotFoundException;
import com.doctor.backend.repository.user.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
//@RequestMapping("/api")
public class PatientController {
    private final PatientRepository patientRepository;
    Logger logger = LoggerFactory.getLogger(PatientController.class);

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping("/patients")
    public List<PatientDto> all() {
        List<PatientDto> patients = new ArrayList<>();
        patientRepository.findAll().forEach(patient -> patients.add(PatientDto.fromEntity(patient)));
        return patients;
    }

    @GetMapping("/patient")
    public ResponseEntity<PatientDto> patientById(@RequestParam Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(PatientDto.fromEntity(patientRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("PatientNotFound")
        )));
    }

    @PutMapping("patient/{id}")
    public PatientDto updatePatient(@PathVariable(value = "id") Long id, @RequestBody PatientDto updatedPatient) throws ResourceNotFoundException {

        return PatientDto.fromEntity(patientRepository.findById(id).map(patient -> {
            patient.setId(updatedPatient.getId());
            patient.setFirstName(updatedPatient.getFirstName());
            patient.setLastName(updatedPatient.getLastName());
            patient.setPhoneNumber(updatedPatient.getPhoneNumber());
            patient.setEmail(updatedPatient.getEmail());
            patient.setBirthday(updatedPatient.getBirthday());
            patient.setAddress(AddressDto.toEntity(updatedPatient.getAddress()));
            return patientRepository.save(patient);
        }).orElseThrow(() -> new ResourceNotFoundException("PatientNotFound")));
    }
}
