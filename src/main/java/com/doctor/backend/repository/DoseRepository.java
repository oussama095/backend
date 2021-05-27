package com.doctor.backend.repository;

import com.doctor.backend.model.Dose;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoseRepository extends JpaRepository<Dose, Long> {
}
