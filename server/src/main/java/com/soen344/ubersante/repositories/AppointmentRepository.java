package com.soen344.ubersante.repositories;

import com.soen344.ubersante.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Specifies methods used to obtain, modify {@link Appointment} related information
 * which is stored in the database.
 */
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Override
    @Transactional
    List<Appointment> findAll();

    @Override
    @Transactional
    <S extends Appointment> S save(S s);

    @Query(value = "SELECT * FROM us_db.appointment WHERE patient_id = ?1", nativeQuery = true)
    List<Appointment> findAppointmentByPatientId(Long patient_id);

    @Transactional 
    @Modifying 
    @Query(value = "DELETE FROM us_db.appointment WHERE id = ?1", nativeQuery = true)
    void deleteAppointmentById(Long patient_id);

    @Query(value = "SELECT * FROM us_db.appointment WHERE patient_id = ?1 AND appointment_type = 1", nativeQuery = true)
    List<Appointment> findAllAnnualBookingsForPatient(long id);

}
