package com.doctor.backend.service;

import com.doctor.backend.model.Patient;

import java.util.List;

public interface PatientService {
    List<Patient> getAllPatients();

    Patient getPatientById(Long patientId);

    Patient addPatient(Patient patient);

    Patient updatePatient(Patient patient);

    void deletePatient(Long patientId);

}
