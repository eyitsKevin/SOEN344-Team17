package com.soen344.ubersante.repositories;

import com.soen344.ubersante.models.Doctor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DoctorRepository extends CrudRepository<Doctor, Long> {

    Doctor findByPermitNumber(String permitNumber);

    List<Doctor> findAllByClinicId(long clinicId);

}
