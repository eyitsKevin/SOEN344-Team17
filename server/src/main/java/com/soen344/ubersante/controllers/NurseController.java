package com.soen344.ubersante.controllers;

import com.soen344.ubersante.dto.NurseLoginForm;
import com.soen344.ubersante.exceptions.InvalidPasswordException;
import com.soen344.ubersante.exceptions.NurseNotFoundException;
import com.soen344.ubersante.services.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/nurses")
public class NurseController {

    @Autowired
    private NurseService nurseService;

    @PostMapping("/login")
    public ResponseEntity loginPatient(@Valid @RequestBody final NurseLoginForm nurseLoginForm) {
        try {
            return new ResponseEntity<>(nurseService.validateNurseLogin(nurseLoginForm), HttpStatus.OK);
        } catch (NurseNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (InvalidPasswordException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

}
