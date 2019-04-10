package com.soen344.ubersante.services;

import com.soen344.ubersante.dto.LoginForm;
import com.soen344.ubersante.dto.NurseRegistrationForm;
import com.soen344.ubersante.exceptions.NurseRegistrationException;
import com.soen344.ubersante.models.Nurse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final String USERNAME = "admin";
    private final String PASSWORD = "admin";

    @Autowired
    private NurseService nurseService;

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

}
