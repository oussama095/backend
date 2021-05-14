package com.doctor.backend.repository;

import com.doctor.backend.model.Dose;
import org.springframework.data.repository.CrudRepository;

public interface DoseRepository extends CrudRepository<Dose, Long> {
}
