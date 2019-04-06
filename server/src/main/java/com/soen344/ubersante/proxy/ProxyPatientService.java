package com.soen344.ubersante.proxy;

import com.soen344.ubersante.services.IPatientService;
import com.soen344.ubersante.models.Patient;
import com.soen344.ubersante.dto.PatientRegistrationForm;
import com.soen344.ubersante.exceptions.PatientAlreadyExistsException;
import com.soen344.ubersante.services.PatientService;
import com.soen344.ubersante.dto.PatientDetails;
import com.soen344.ubersante.dto.PatientLoginForm;
import com.soen344.ubersante.exceptions.PatientNotFoundException;
import com.soen344.ubersante.exceptions.InvalidPasswordException;

public class ProxyPatientService implements IPatientService {
    private PatientService patientService = new PatientService();
    private Patient patient;
    private PatientDetails patientDetails;
    @Override
    public Patient registerNewPatient(PatientRegistrationForm patientRegistrationForm) throws PatientAlreadyExistsException{
        if(patient == null){
        patient = patientService.registerNewPatient(patientRegistrationForm);
        }
        return patient;
    }

    @Override
    public PatientDetails validateLogin(PatientLoginForm loginForm) throws PatientNotFoundException, InvalidPasswordException{
       if(patientDetails == null){
        patientDetails = patientService.validateLogin(loginForm);
       }
       return patientDetails;
    }

}