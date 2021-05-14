package com.doctor.backend.repository;

import com.doctor.backend.model.Transcription;
import org.springframework.data.repository.CrudRepository;

public interface TranscriptionRepository extends CrudRepository<Transcription, Long> {
}
