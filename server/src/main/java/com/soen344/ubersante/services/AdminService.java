package com.soen344.ubersante.services;

import com.soen344.ubersante.dto.LoginForm;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final String USERNAME = "admin";
    private final String PASSWORD = "admin";

    // hardcoded admin for milestone 2
    public Boolean validateLogin(LoginForm form) {
        boolean validUser = form.getUserName().equals(USERNAME);
        boolean validPassword = form.getPassword().equals(PASSWORD);

        return validUser && validPassword;

    }

}
