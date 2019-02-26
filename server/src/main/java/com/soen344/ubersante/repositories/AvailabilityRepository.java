package com.soen344.ubersante.repositories;

import java.util.List;

import com.soen344.ubersante.models.Availability;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AvailabilityRepository extends CrudRepository<Availability, Long> {

    @Query(value = "SELECT * FROM us_db.doctor_availability WHERE MONTH(start_time) = ?1 AND appointment_type = ?2", nativeQuery = true)
    List<Availability> findAvailabilitiesByMonth(String month, String availabilityType); 

} 
