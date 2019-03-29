package com.soen344.ubersante.repositories;

import com.soen344.ubersante.models.Clinic;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ClinicRepository extends CrudRepository<Clinic, Long> {

    List<Clinic> findAll();

}
