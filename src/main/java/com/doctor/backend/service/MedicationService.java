package com.doctor.backend.service;

import com.doctor.backend.model.Medication;

public interface MedicationService {
    void removeMedicationFromTranscription(Long medicationId);

    Medication updateMedication(Medication medication);

}
