package com.doctor.backend.repository;

import com.doctor.backend.model.Medication;
import org.springframework.data.repository.CrudRepository;

public interface MedicationRepository extends CrudRepository<Medication, Long> {
}
