package com.doctor.backend.service;

import com.doctor.backend.model.Medication;
import com.doctor.backend.model.Patient;
import com.doctor.backend.model.Transcription;
import com.doctor.backend.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientServiceImp implements PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientServiceImp(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        patientRepository.findAll().forEach(patients::add);
        return patients;
    }

    @Override
    public Patient getPatientById(Long patientId) {
        var optionalPatient = patientRepository.findById(patientId);
        return optionalPatient.orElse(null);
    }

    @Override
    public Patient addPatient(Patient patient) {
        var newPatient = new Patient();
        newPatient.setFirstName(patient.getFirstName());
        newPatient.setLastName(patient.getLastName());
        newPatient.setPhoneNumber(patient.getPhoneNumber());
        newPatient.setEmail(patient.getEmail());
        newPatient.setBirthday(patient.getBirthday());
        newPatient.setAddress(patient.getAddress());
        return patientRepository.save(newPatient);

    }

    @Override
    public Patient updatePatient(Patient patient) {
        var optionalPatient = patientRepository.findById(patient.getId());
        if (optionalPatient.isPresent()) {
            var newPatient = new Patient();
            newPatient.setId(patient.getId());
            newPatient.setFirstName(patient.getFirstName());
            newPatient.setLastName(patient.getLastName());
            newPatient.setPhoneNumber(patient.getPhoneNumber());
            newPatient.setEmail(patient.getEmail());
            newPatient.setBirthday(patient.getBirthday());
            newPatient.setAddress(patient.getAddress());
            return patientRepository.save(newPatient);
        }
        return null;
    }

    @Override
    public void deletePatient(Long patientId) {

        var optionalPatient = patientRepository.findById(patientId);
        if (optionalPatient.isPresent()) {
            patientRepository.deleteById(patientId);
        }
    }

    @Override
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public List<Transcription> getTranscriptionByPatient(Long patientId) {
        var patient = patientRepository.findById(patientId).orElse(null);
        if (patient != null) {
            return patient.getTranscriptions();
        }
        return new ArrayList<>();
    }

    @Override
    public List<Medication> getMedicationByPatient(Long patientId) {
        var patient = patientRepository.findById(patientId).orElse(null);
        if (patient != null) {
            var medications = new ArrayList<Medication>();
            patient.getTranscriptions().forEach(transcription -> medications.addAll(transcription.getMedications()));
            return medications;
        }
        return new ArrayList<>();
    }

    @Override
    public Transcription addTranscription(Long patientId, Transcription transcription) {
        var patient = patientRepository.findById(patientId).orElse(null);
        if (patient != null) {

            patient.getTranscriptions().add(transcription);
            return patientRepository.save(patient).getTranscriptions()
                    .stream()
                    .filter(t -> t.getMedications() == transcription.getMedications() && t.getNote().equals(transcription.getNote()))
                    .findAny().orElse(null);
        }
        return null;
    }
}
