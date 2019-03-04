package com.soen344.ubersante.repositories;

import com.soen344.ubersante.models.Availability;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AvailabilityRepository extends CrudRepository<Availability, Long> {

    @Query(value = "SELECT * FROM us_db.doctor_availability WHERE MONTH(start_time) = ?1 AND appointment_type = ?2 AND appointment_id IS NULL", nativeQuery = true)
    List<Availability> findAvailabilitiesByMonth(String month, String availabilityType);

    @Transactional
    @Modifying
    @Query(value = "UPDATE doctor_availability a SET a.appointment_id = ?2 WHERE a.id = ?1", nativeQuery = true)
    void addAppointmentToAvailability(Long availabilityId, Long appointmentId);

}
