package com.soen344.ubersante.services;

import com.soen344.ubersante.dto.PatientDetails;
import com.soen344.ubersante.dto.PatientLoginForm;
import com.soen344.ubersante.dto.PatientRegistrationForm;
import com.soen344.ubersante.exceptions.InvalidPasswordException;
import com.soen344.ubersante.exceptions.PatientAlreadyExistsException;
import com.soen344.ubersante.exceptions.PatientNotFoundException;
import com.soen344.ubersante.models.Patient;
import com.soen344.ubersante.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PatientService implements IPatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private IPatientService patientService;

    @Transactional
    public Patient registerNewPatient(PatientRegistrationForm patientRegistrationForm) throws PatientAlreadyExistsException {

        if (emailExists(patientRegistrationForm.getEmail())) {
            throw new PatientAlreadyExistsException("An account with that email already exists: " + patientRegistrationForm.getEmail());
        }

        if (healthCardExists(patientRegistrationForm.getHealthCard())) {
            throw new PatientAlreadyExistsException("An account with that health card already exists: " + patientRegistrationForm.getHealthCard());
        }

        final Patient patient = new Patient();

        patient.setHealthCard(patientRegistrationForm.getHealthCard());
        patient.setFirstName(patientRegistrationForm.getFirstName());
        patient.setLastName(patientRegistrationForm.getLastName());
        patient.setBirthday(patientRegistrationForm.getBirthday());
        patient.setGender(patientRegistrationForm.getGender());
        patient.setPhone(patientRegistrationForm.getPhone());
        patient.setEmail(patientRegistrationForm.getEmail());
        patient.setAddress(patientRegistrationForm.getAddress());

        // This is where you would encrypt!
        patient.setPassword(patientRegistrationForm.getPassword());

        return patientRepository.save(patient);
    }

    @Transactional
    public PatientDetails validateLogin(PatientLoginForm loginForm) throws PatientNotFoundException, InvalidPasswordException {

        String healthCard = loginForm.getHealthCard();
        Patient patient = patientRepository.findByHealthCard(healthCard);

        if (patient == null) {
            throw new PatientNotFoundException("Patient with health card '" + healthCard +"' is not registered" );
        }

        if (!patient.getPassword().equals(loginForm.getPassword())) {
            throw new InvalidPasswordException("Invalid password for health card '" + healthCard + "'");
        }

        return new PatientDetails(patient);
    }

    private boolean emailExists(String email) {
        Patient patient = patientRepository.findByEmail(email);

        if (patient != null) {
            return true;
        }

        return false;
    }

    private boolean healthCardExists(String healthCard) {
        Patient patient = patientRepository.findByHealthCard(healthCard);

        if (patient != null) {
            return true;
        }

        return false;
    }
}
