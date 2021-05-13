package com.doctor.backend.service;

import com.doctor.backend.model.Patient;
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
            // TODO get all notification and delete them
            // TODO get all appointments and delete them
            patientRepository.deleteById(patientId);
        }
    }
}
