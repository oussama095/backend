package com.doctor.backend.service;

import com.doctor.backend.model.Medication;
import com.doctor.backend.model.Transcription;
import com.doctor.backend.repository.TranscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TranscriptionServiceImp implements TranscriptionService {

    private final TranscriptionRepository transcriptionRepository;

    @Autowired
    public TranscriptionServiceImp(TranscriptionRepository transcriptionRepository) {
        this.transcriptionRepository = transcriptionRepository;
    }

    @Override
    public void deleteTranscription(Long transcriptionId) {
        transcriptionRepository.deleteById(transcriptionId);

    }

    @Override
    public Transcription updateTranscription(Transcription transcription) {
        var oldTranscription = transcriptionRepository.findById(transcription.getId()).orElse(null);
        if (oldTranscription != null) {
            oldTranscription.setNote(transcription.getNote());
            oldTranscription.setMedications(transcription.getMedications());
            return transcriptionRepository.save(oldTranscription);
        }
        return null;
    }

    @Override
    public Medication addMedicationToTranscription(Long transcriptionId, Medication medication) {
        var oldTranscription = transcriptionRepository.findById(transcriptionId).orElse(null);
        if (oldTranscription != null) {
            oldTranscription.getMedications().add(medication);
            return transcriptionRepository.save(oldTranscription)
                    .getMedications()
                    .stream().filter(m -> m.getName().equals(medication.getName())
                            && m.getRoute().equals(medication.getRoute())
                            && m.getDrugForm().equals(medication.getDrugForm())
                            && m.getDose() == medication.getDose())
                    .findAny().orElse(null);
        }
        return null;
    }

    @Override
    public List<Medication> getMedicationByTranscription(Long transcriptionId) {
        var transcription = transcriptionRepository.findById(transcriptionId).orElse(null);
        if (transcription != null) {
            return transcription.getMedications();
        }
        return new ArrayList<>();
    }
}
