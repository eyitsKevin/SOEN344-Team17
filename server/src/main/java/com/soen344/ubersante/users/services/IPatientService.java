package com.soen344.ubersante.users.services;

import com.soen344.ubersante.users.dto.PatientRegistrationForm;
import com.soen344.ubersante.users.exceptions.PatientAlreadyExistsException;
import com.soen344.ubersante.users.models.Patient;

public interface IPatientService {

    Patient registerNewPatient(PatientRegistrationForm patientRegistrationForm) throws PatientAlreadyExistsException;
}
