package com.doctor.backend.repository.user;

import com.doctor.backend.model.Patient;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<Patient, Long> {
}
