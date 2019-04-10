package com.soen344.ubersante.controllers;

import com.soen344.ubersante.services.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/clinics")
public class ClinicController {

    @Autowired
    private ClinicService clinicService;
    
    @GetMapping("/view")
    public ResponseEntity viewClinics() {

        return new ResponseEntity<>(clinicService.getAllClinics(), HttpStatus.OK);
        
    }

    @GetMapping("/view/doctors/{id}")
    public ResponseEntity viewDoctorsByClinic(@PathVariable long id) {
        return new ResponseEntity<>(clinicService.getDoctorByClinicId(id), HttpStatus.OK);
    }

    @GetMapping("/view/nurses/{id}")
    public ResponseEntity viewNursesByClinic(@PathVariable long id) {
        return new ResponseEntity<>(clinicService.getNurseByClinicId(id), HttpStatus.OK);
    }

}
