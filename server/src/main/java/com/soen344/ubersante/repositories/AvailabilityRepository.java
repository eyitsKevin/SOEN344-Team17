package com.soen344.ubersante.repositories;

import java.time.LocalDateTime;
import java.util.List;

import com.soen344.ubersante.models.Availability;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AvailabilityRepository extends CrudRepository<Availability, Long> {
    @Query(value = "SELECT * FROM us_db.doctor_availability WHERE MONTH(start_time) = ?1 AND appointment_type = ?2", nativeQuery = true)
    List<Availability> findAvailabilitiesByMonth(String month, String availabilityType);

    List<Availability> findAllByDoctorPermitNumber(String permitNumber);

    @Query(value = "SELECT * FROM doctor_availability " +
            "WHERE start_time >= ?1 AND end_time <= ?2 AND doctor_permit_number = ?3", nativeQuery = true)
    List<Availability> findAllInDateRangeForDoctor(LocalDateTime startTime, LocalDateTime endTime, String permit);

} 
