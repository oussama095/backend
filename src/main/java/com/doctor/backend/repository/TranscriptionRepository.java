package com.doctor.backend.repository;

import com.doctor.backend.model.Patient;
import com.doctor.backend.model.Transcription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TranscriptionRepository extends JpaRepository<Transcription, Long> {
    Optional<List<Transcription>> findTranscriptionsByPatient(Patient patientId);

}
