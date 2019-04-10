package com.soen344.ubersante.controllers;

import com.soen344.ubersante.services.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
