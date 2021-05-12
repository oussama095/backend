package com.doctor.backend.repository;

import com.doctor.backend.model.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}
