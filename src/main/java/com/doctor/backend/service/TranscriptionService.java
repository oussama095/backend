package com.doctor.backend.service;

import com.doctor.backend.model.Medication;
import com.doctor.backend.model.Transcription;

import java.util.List;

public interface TranscriptionService {

    void deleteTranscription(Long transcriptionId);

    Transcription updateTranscription(Transcription transcription);

    Medication addMedicationToTranscription(Long transcriptionId, Medication medication);

    List<Medication> getMedicationByTranscription(Long transcriptionId);


}
