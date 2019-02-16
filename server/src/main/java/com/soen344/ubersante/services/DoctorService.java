package com.soen344.ubersante.services;

import com.soen344.ubersante.dto.DoctorDetails;
import com.soen344.ubersante.dto.DoctorLoginForm;
import com.soen344.ubersante.exceptions.DoctorNotFoundException;
import com.soen344.ubersante.exceptions.InvalidPasswordException;
import com.soen344.ubersante.models.Doctor;
import com.soen344.ubersante.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

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
}
