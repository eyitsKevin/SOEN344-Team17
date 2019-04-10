package com.soen344.ubersante.services;

import com.soen344.ubersante.dto.NurseDetails;
import com.soen344.ubersante.dto.NurseLoginForm;
import com.soen344.ubersante.dto.NurseRegistrationForm;
import com.soen344.ubersante.exceptions.InvalidPasswordException;
import com.soen344.ubersante.exceptions.NurseRegistrationException;
import com.soen344.ubersante.exceptions.NurseNotFoundException;
import com.soen344.ubersante.models.Clinic;
import com.soen344.ubersante.models.Nurse;
import com.soen344.ubersante.repositories.ClinicRepository;
import com.soen344.ubersante.repositories.NurseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class NurseService implements INurseService {

    @Autowired
    private NurseRepository nurseRepository;

    @Autowired
    private ClinicRepository clinicRepository;

    public NurseDetails validateNurseLogin(NurseLoginForm nurseLoginForm)
            throws NurseNotFoundException, InvalidPasswordException {

        String accessId = nurseLoginForm.getAccessId();
        Nurse nurse = nurseRepository.findAccessByAccessId(accessId);

        if (nurse == null) {
            throw new NurseNotFoundException("Nurse with access card '" + accessId +"' does not exist" );
        }

        if (!nurse.getPassword().equals(nurseLoginForm.getPassword())) {
            throw new InvalidPasswordException("Invalid password for access card '" + accessId + "'");
        }

        return new NurseDetails(nurse);
    }

    public Nurse registerNewNurse(NurseRegistrationForm nurseRegistrationForm) throws NurseRegistrationException {

        if (accessIdExists(nurseRegistrationForm.getAccessId())) {
            throw new NurseRegistrationException("An account with that access id already exists");
        }

        Clinic clinic;
        try {
            clinic = clinicRepository.findById(nurseRegistrationForm.getClinicId()).get();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            throw new NurseRegistrationException("No clinic associated with that id");
        }


        final Nurse nurse = new Nurse();

        nurse.setAccessId(nurseRegistrationForm.getAccessId());
        nurse.setFirstName(nurseRegistrationForm.getFirstName());
        nurse.setLastName(nurseRegistrationForm.getLastName());
        nurse.setPassword(nurseRegistrationForm.getPassword());
        nurse.setClinic(clinic);

        return nurseRepository.save(nurse);
    }

    private boolean accessIdExists(String accessId) {
        Nurse nurse = nurseRepository.findAccessByAccessId(accessId);
        return nurse != null;
    }
}
