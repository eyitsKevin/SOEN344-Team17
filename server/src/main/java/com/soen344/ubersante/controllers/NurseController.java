package com.soen344.ubersante.controllers;

import com.soen344.ubersante.dto.NurseLoginForm;
import com.soen344.ubersante.exceptions.InvalidPasswordException;
import com.soen344.ubersante.exceptions.NurseNotFoundException;
import com.soen344.ubersante.exceptions.PatientNotFoundException;
import com.soen344.ubersante.services.NurseService;
import com.soen344.ubersante.services.PatientService;
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

    @Autowired
    private PatientService patientService;

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

    @RequestMapping(value = "/patients", method = RequestMethod.GET)
    public ResponseEntity getAllPatient() {
        try {
            return new ResponseEntity<>(patientService.findAll(), HttpStatus.OK);
        } catch (PatientNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


}
