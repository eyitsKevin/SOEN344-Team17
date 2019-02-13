package com.soen344.ubersante.repositories;

import com.soen344.ubersante.models.Patient;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<Patient, Long> {

    Patient findByEmail(String email);

    Patient findByHealthCard(String healthCard);
}
