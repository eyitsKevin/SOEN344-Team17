package com.soen344.ubersante.controllers;

import com.soen344.ubersante.dto.*;
import com.soen344.ubersante.exceptions.DoctorRegistrationException;
import com.soen344.ubersante.exceptions.NurseRegistrationException;
import com.soen344.ubersante.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public ResponseEntity<Boolean> loginAdmin(@Valid @RequestBody final LoginForm loginForm) {

        boolean validLogin = adminService.validateLogin(loginForm);

        return validLogin ? new ResponseEntity<>(true, HttpStatus.OK) : new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);

    }

    @PostMapping("register/nurse")
    public ResponseEntity<Boolean> registerNewNurse(@Valid @RequestBody final NurseRegistrationForm registrationForm) {
        try {
           return new ResponseEntity<>(adminService.registerNewNurse(registrationForm), HttpStatus.OK);
        } catch (NurseRegistrationException e) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("register/doctor")
    public ResponseEntity<Boolean> registerNewDoctor(@Valid @RequestBody final DoctorRegistrationForm registrationForm) {
        try {
            return new ResponseEntity<>(adminService.registerNewDoctor(registrationForm), HttpStatus.OK);
        } catch (DoctorRegistrationException e) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("register/clinic")
    public ResponseEntity<Boolean> registerNewClinic(@Valid @RequestBody final ClinicRegistrationForm registrationForm) {
        try {
            return new ResponseEntity<>(adminService.registerNewClinic(registrationForm), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/nurse")
    public ResponseEntity<Boolean> updateNurse(@Valid @RequestBody final NurseDetails nurseDetails) {
        try {
            return new ResponseEntity<>(adminService.modifyNurse(nurseDetails), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/doctor")
    public ResponseEntity<Boolean> updateDoctor(@Valid @RequestBody final DoctorDetails doctorDetails) {
        try {
            return new ResponseEntity<>(adminService.modifyDoctor(doctorDetails), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/nurse")
    public ResponseEntity<Boolean> deleteNurse(@Valid @RequestBody final NurseDetails nurseDetails) {
        try {
            return new ResponseEntity<>(adminService.deleteNurse(nurseDetails), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/doctor")
    public ResponseEntity<Boolean> deleteDoctor(@Valid @RequestBody final DoctorDetails doctorDetails) {
        try {
            return new ResponseEntity<>(adminService.deleteDoctor(doctorDetails), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }


}
