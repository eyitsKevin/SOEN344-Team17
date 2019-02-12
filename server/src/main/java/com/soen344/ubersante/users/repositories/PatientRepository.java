package com.soen344.ubersante.users.repositories;

import com.soen344.ubersante.users.models.Patient;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<Patient, Long> {
}
