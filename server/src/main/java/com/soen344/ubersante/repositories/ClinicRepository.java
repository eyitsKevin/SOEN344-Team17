package com.soen344.ubersante.repositories;

import com.soen344.ubersante.models.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClinicRepository extends JpaRepository<Clinic, Long> {

    List<Clinic> findAll();

    @Override
    Clinic getOne(Long aLong);
}
