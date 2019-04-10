package com.soen344.ubersante.services;

import com.soen344.ubersante.dto.DoctorDetails;
import com.soen344.ubersante.dto.DoctorLoginForm;
import com.soen344.ubersante.dto.DoctorRegistrationForm;
import com.soen344.ubersante.exceptions.DoctorNotFoundException;
import com.soen344.ubersante.exceptions.DoctorRegistrationException;
import com.soen344.ubersante.exceptions.InvalidPasswordException;
import com.soen344.ubersante.models.Clinic;
import com.soen344.ubersante.models.Doctor;
import com.soen344.ubersante.repositories.ClinicRepository;
import com.soen344.ubersante.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private ClinicRepository clinicRepository;

    public DoctorDetails validateDoctorLogin(DoctorLoginForm loginForm)
            throws DoctorNotFoundException, InvalidPasswordException {

        String permitNumber = loginForm.getPermitNumber();
        Doctor doctor = doctorRepository.findByPermitNumber(permitNumber);

        if (doctor == null) {
            throw new DoctorNotFoundException("Doctor with permit number '" + permitNumber +"' does not exist" );
        }

        if (!(loginForm.getPassword().equals(doctor.getPassword()))) {
            throw new InvalidPasswordException("Invalid password for permit number '" + permitNumber + "'");
        }

        return new DoctorDetails(doctor);
    }

    @Transactional
    public Doctor registerNewDoctor(DoctorRegistrationForm doctorRegistrationForm) throws DoctorRegistrationException {

        if (permitNumberExists(doctorRegistrationForm.getPermitNumber())) {
            throw new DoctorRegistrationException("An account with that permit number already exists");
        }

        Clinic clinic;
        try {
            clinic = clinicRepository.findById(doctorRegistrationForm.getClinicId()).get();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            throw new DoctorRegistrationException("No clinic associated with that id");
        }

        final Doctor doctor = new Doctor();

        doctor.setPermitNumber(doctorRegistrationForm.getPermitNumber());
        doctor.setFirstName(doctorRegistrationForm.getFirstName());
        doctor.setLastName(doctorRegistrationForm.getLastName());
        doctor.setCity(doctorRegistrationForm.getCity());
        doctor.setSpecialty(doctorRegistrationForm.getSpecialty());
        doctor.setPassword(doctorRegistrationForm.getPassword());
        doctor.setClinic(clinic);

        return doctorRepository.save(doctor);
    }

    private boolean permitNumberExists(String permitNumber) {
        Doctor doctor = doctorRepository.findByPermitNumber(permitNumber);
        return doctor != null;
    }
}
