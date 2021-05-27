package com.doctor.backend.service;

import com.doctor.backend.model.Medication;
import com.doctor.backend.model.Patient;
import com.doctor.backend.model.Transcription;

import java.util.List;

public interface PatientService {
    List<Patient> getAllPatients();

    Patient getPatientById(Long patientId);

    Patient addPatient(Patient patient);

    Patient updatePatient(Patient patient);

    void deletePatient(Long patientId);

    Patient save(Patient patient);

    List<Transcription> getTranscriptionByPatient(Long patientId);

    List<Medication> getMedicationByPatient(Long patientId);

    Transcription addTranscription(Long patientId, Transcription transcription);


}
