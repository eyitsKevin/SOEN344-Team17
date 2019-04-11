package com.soen344.ubersante.services;

import com.soen344.ubersante.dto.*;
import com.soen344.ubersante.exceptions.DoctorRegistrationException;
import com.soen344.ubersante.exceptions.NurseRegistrationException;
import com.soen344.ubersante.models.Clinic;
import com.soen344.ubersante.models.Doctor;
import com.soen344.ubersante.models.Nurse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final String USERNAME = "admin";
    private final String PASSWORD = "admin";

    @Autowired
    private NurseService nurseService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private ClinicService clinicService;

    // hardcoded admin for milestone 2
    public Boolean validateLogin(LoginForm form) {
        boolean validUser = form.getUserName().equals(USERNAME);
        boolean validPassword = form.getPassword().equals(PASSWORD);

        return validUser && validPassword;

    }

    public Boolean registerNewNurse(NurseRegistrationForm nurseRegistrationForm) throws NurseRegistrationException {
        Nurse newNurse = nurseService.registerNewNurse(nurseRegistrationForm);

        return newNurse != null;
    }

    public Boolean registerNewDoctor(DoctorRegistrationForm doctorRegistrationForm) throws DoctorRegistrationException {
        Doctor newDoctor = doctorService.registerNewDoctor(doctorRegistrationForm);

        return newDoctor != null;
    }

    public Boolean registerNewClinic(ClinicRegistrationForm clinicRegistrationForm) {
        Clinic newClinic = clinicService.registerNewClinic(clinicRegistrationForm);

        return newClinic != null;
    }

    public Boolean modifyNurse(NurseDetails nurseDetails) {
        Nurse nurse = nurseService.modifyNurse(nurseDetails);

        return nurse != null;
    }

}
