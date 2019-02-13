package com.soen344.ubersante.services;

import com.soen344.ubersante.dto.PatientRegistrationForm;
import com.soen344.ubersante.exceptions.PatientAlreadyExistsException;
import com.soen344.ubersante.models.Patient;

public interface IPatientService {

    Patient registerNewPatient(PatientRegistrationForm patientRegistrationForm) throws PatientAlreadyExistsException;
}
