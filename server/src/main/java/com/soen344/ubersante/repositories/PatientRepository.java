package com.soen344.ubersante.repositories;

import com.soen344.ubersante.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Patient findByEmail(String email);

    Patient findByHealthCard(String healthCard);

    @Override
    List<Patient> findAll();
}
