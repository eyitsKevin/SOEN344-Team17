package com.soen344.ubersante.repositories;

import com.soen344.ubersante.models.Nurse;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NurseRepository extends CrudRepository<Nurse, Long> {

    Nurse findAccessByAccessId(String accessId);

    List<Nurse> findAllByClinicId(long id);
}
