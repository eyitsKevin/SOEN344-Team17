package com.soen344.ubersante.users.services;

import com.soen344.ubersante.users.dto.PatientDto;
import com.soen344.ubersante.users.exceptions.PatientAlreadyExistsException;
import com.soen344.ubersante.users.models.Patient;
import com.soen344.ubersante.users.repositories.PatientRepository;
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
    public Patient registerNewPatient(PatientDto patientDto) throws PatientAlreadyExistsException {

        if (emailExists(patientDto.getEmail())) {
            throw new PatientAlreadyExistsException("An account with that email already exists: " + patientDto.getEmail());
        }

        if (healthCardExists(patientDto.getHealthCard())) {
            throw new PatientAlreadyExistsException("An account with that health card already exists: " + patientDto.getHealthCard());
        }

        final Patient patient = new Patient();

        patient.setHealthCard(patientDto.getHealthCard());
        patient.setFirstName(patientDto.getFirstName());
        patient.setLastName(patientDto.getLastName());
        patient.setBirthday(patientDto.getBirthday());
        patient.setGender(patientDto.getGender());
        patient.setPhone(patientDto.getPhone());
        patient.setEmail(patientDto.getEmail());
        patient.setAddress(patientDto.getAddress());

        // This is where you would encrypt!
        patient.setPassword(patientDto.getPassword());

        return patientRepository.save(patient);

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
