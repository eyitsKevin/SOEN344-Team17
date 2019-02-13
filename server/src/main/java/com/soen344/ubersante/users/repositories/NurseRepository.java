package com.soen344.ubersante.users.repositories;

import com.soen344.ubersante.users.models.Nurse;
import org.springframework.data.repository.CrudRepository;

public interface NurseRepository extends CrudRepository<Nurse, Long> {
}
