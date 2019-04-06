package com.soen344.ubersante.services;

import com.soen344.ubersante.dto.PatientRegistrationForm;
import com.soen344.ubersante.exceptions.PatientAlreadyExistsException;
import com.soen344.ubersante.models.Patient;
import com.soen344.ubersante.dto.PatientDetails;
import com.soen344.ubersante.dto.PatientLoginForm;
import com.soen344.ubersante.exceptions.PatientNotFoundException;
import com.soen344.ubersante.exceptions.InvalidPasswordException;

public interface IPatientService {

    Patient registerNewPatient(PatientRegistrationForm patientRegistrationForm) throws PatientAlreadyExistsException;
    PatientDetails validateLogin(PatientLoginForm loginForm) throws PatientNotFoundException, InvalidPasswordException;
}
