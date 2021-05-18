package com.doctor.backend.service;

import com.doctor.backend.model.Medication;
import com.doctor.backend.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicationServiceImp implements MedicationService {

    private final MedicationRepository medicationRepository;

    @Autowired
    public MedicationServiceImp(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    @Override
    public void removeMedicationFromTranscription(Long medicationId) {
        medicationRepository.deleteById(medicationId);
    }

    @Override
    public Medication getMedicationById(Long medicationId) {
        return medicationRepository.findById(medicationId).orElse(null);
    }

    @Override
    public Medication updateMedication(Medication medication) {
        var oldMedication = medicationRepository.findById(medication.getId()).orElse(null);
        if (oldMedication != null) {
            oldMedication.setDose(medication.getDose());
            oldMedication.setDrugForm(medication.getDrugForm());
            oldMedication.setName(medication.getName());
            oldMedication.setRoute(medication.getRoute());
            return medicationRepository.save(oldMedication);
        }
        return null;
    }
}
