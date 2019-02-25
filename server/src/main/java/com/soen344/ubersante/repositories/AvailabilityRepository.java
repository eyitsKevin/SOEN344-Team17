package com.soen344.ubersante.repositories;

import java.time.LocalDateTime;
import java.util.List;

import com.soen344.ubersante.models.Availability;
import org.springframework.data.repository.CrudRepository;

public interface AvailabilityRepository extends CrudRepository<Availability, Long> {

    List<Availability> findByAllByMonth(LocalDateTime month);

}
