package com.soen344.ubersante.users.repositories;

import com.soen344.ubersante.users.models.Doctor;
import org.springframework.data.repository.CrudRepository;

public interface DoctorRepository extends CrudRepository<Doctor, Long> {

}
