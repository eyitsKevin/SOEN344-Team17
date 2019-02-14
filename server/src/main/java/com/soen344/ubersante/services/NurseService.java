package com.soen344.ubersante.services;

import com.soen344.ubersante.dto.NurseDetails;
import com.soen344.ubersante.dto.NurseLoginForm;
import com.soen344.ubersante.exceptions.InvalidPasswordException;
import com.soen344.ubersante.exceptions.NurseNotFoundException;
import com.soen344.ubersante.models.Nurse;
import com.soen344.ubersante.repositories.NurseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NurseService implements INurseService {

    @Autowired
    private NurseRepository nurseRepository;

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
}
