package com.soen344.ubersante.services;

import com.soen344.ubersante.dto.DoctorDetails;
import com.soen344.ubersante.dto.DoctorLoginForm;
import com.soen344.ubersante.exceptions.DoctorNotFoundException;
import com.soen344.ubersante.exceptions.InvalidPasswordException;

public interface IDoctorService {

    DoctorDetails validateDoctorLogin(DoctorLoginForm loginForm) throws DoctorNotFoundException, InvalidPasswordException;

}
