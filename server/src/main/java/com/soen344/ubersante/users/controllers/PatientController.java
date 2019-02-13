package com.soen344.ubersante.users.controllers;

import com.soen344.ubersante.users.dto.PatientDetails;
import com.soen344.ubersante.users.dto.PatientLoginForm;
import com.soen344.ubersante.users.dto.PatientRegistrationForm;
import com.soen344.ubersante.users.exceptions.InvalidPasswordException;
import com.soen344.ubersante.users.exceptions.PatientAlreadyExistsException;
import com.soen344.ubersante.users.exceptions.PatientNotFoundException;
import com.soen344.ubersante.users.models.Patient;
import com.soen344.ubersante.users.repositories.PatientRepository;
import com.soen344.ubersante.users.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientService patientService;

    @GetMapping("/all")
    public List<Patient> getAllPatients() {
        System.out.println("Get all Patients...");

        List<Patient> patients = new ArrayList<>();
        patientRepository.findAll().forEach(patients::add);

        return patients;
    }

    @PostMapping("/registration")
    public ResponseEntity registerNewPatient(@Valid @RequestBody final PatientRegistrationForm registrationForm) {

        try {
            patientService.registerNewPatient(registrationForm);
        } catch (PatientAlreadyExistsException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>("Patient Successfully Registered", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity loginPatient(@Valid @RequestBody final PatientLoginForm loginForm) {
        try {
           return new ResponseEntity<>(patientService.validateLogin(loginForm), HttpStatus.OK);
        } catch (PatientNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (InvalidPasswordException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
}
