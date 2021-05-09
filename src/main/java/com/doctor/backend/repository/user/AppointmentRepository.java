package com.doctor.backend.repository.user;

import com.doctor.backend.model.Appointment;
import org.springframework.data.repository.CrudRepository;

public interface AppointmentRepository extends CrudRepository<Appointment, Long> {
}