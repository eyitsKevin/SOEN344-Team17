package com.soen344.ubersante.services;

import com.soen344.ubersante.dto.NurseDetails;
import com.soen344.ubersante.dto.NurseLoginForm;
import com.soen344.ubersante.exceptions.InvalidPasswordException;
import com.soen344.ubersante.exceptions.NurseNotFoundException;

public interface INurseService {

    NurseDetails validateNurseLogin(NurseLoginForm loginForm) throws NurseNotFoundException, InvalidPasswordException;
}
