package com.soen344.ubersante.services;

import com.soen344.ubersante.dto.PatientDetails;
import com.soen344.ubersante.dto.PatientLoginForm;
import com.soen344.ubersante.dto.PatientRegistrationForm;
import com.soen344.ubersante.exceptions.IllegalBirthdayException;
import com.soen344.ubersante.exceptions.InvalidPasswordException;
import com.soen344.ubersante.exceptions.PatientAlreadyExistsException;
import com.soen344.ubersante.exceptions.PatientNotFoundException;
import com.soen344.ubersante.models.Patient;
import com.soen344.ubersante.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

@Service
public class PatientService implements IPatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Transactional
    public Patient registerNewPatient(PatientRegistrationForm patientRegistrationForm) throws PatientAlreadyExistsException {

        if (emailExists(patientRegistrationForm.getEmail())) {
            throw new PatientAlreadyExistsException("An account with that email already exists: " + patientRegistrationForm.getEmail());
        }

        if (healthCardExists(patientRegistrationForm.getHealthCard())) {
            throw new PatientAlreadyExistsException("An account with that health card already exists: " + patientRegistrationForm.getHealthCard());
        }

        LocalDate birthday = LocalDate.parse(patientRegistrationForm.getBirthday().substring(0, 10));

        if (underAge(birthday)) {
            throw new IllegalBirthdayException("Patient cannot be under 18 years of age");
        }


        final Patient patient = new Patient();

        patient.setHealthCard(patientRegistrationForm.getHealthCard());
        patient.setFirstName(patientRegistrationForm.getFirstName());
        patient.setLastName(patientRegistrationForm.getLastName());
        patient.setBirthday(birthday.toString());
        patient.setGender(patientRegistrationForm.getGender());
        patient.setPhone(patientRegistrationForm.getPhone());
        patient.setEmail(patientRegistrationForm.getEmail());
        patient.setAddress(patientRegistrationForm.getAddress());
        patient.setPassword(BCrypt.hashpw(patientRegistrationForm.getPassword(), BCrypt.gensalt()));

        return patientRepository.save(patient);
    }

    @Transactional
    public PatientDetails validateLogin(PatientLoginForm loginForm) throws PatientNotFoundException, InvalidPasswordException {

        String healthCard = loginForm.getHealthCard();
        Patient patient = patientRepository.findByHealthCard(healthCard);

        if (patient == null) {
            throw new PatientNotFoundException("Patient with health card '" + healthCard +"' is not registered" );
        }

        if (!BCrypt.checkpw(loginForm.getPassword(), patient.getPassword())) {
            throw new InvalidPasswordException("Invalid password for health card '" + healthCard + "'");
        }

        return new PatientDetails(patient);
    }

    private boolean emailExists(String email) {
        Patient patient = patientRepository.findByEmail(email);
        return patient != null;

    }

    private boolean healthCardExists(String healthCard) {
        Patient patient = patientRepository.findByHealthCard(healthCard);
        return patient != null;

    }

    public List<PatientDetails> findAll() throws PatientNotFoundException {
        List<PatientDetails> patientDetailsList = new ArrayList<>();
        if (patientRepository.findAll() == null) {
            return new ArrayList<>();
        } else {
            for (Patient patient : patientRepository.findAll()) {
                patientDetailsList.add(new PatientDetails(patient));
            }
        }
        return patientDetailsList;
    }


    private boolean underAge(LocalDate birthday) {
        LocalDate legalAgeBirthday = LocalDate.now().minusYears(18);

       return birthday.isAfter(legalAgeBirthday);
    }
}
