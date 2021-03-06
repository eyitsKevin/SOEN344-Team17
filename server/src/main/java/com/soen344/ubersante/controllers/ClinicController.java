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

    @GetMapping("/view/{id}")
    public ResponseEntity viewByClinic(@PathVariable long id) {
        return new ResponseEntity<>(clinicService.buildClinicDto(id), HttpStatus.OK);
    }

    @GetMapping("/view/all")
    public ResponseEntity viewByClinic() {
        return new ResponseEntity<>(clinicService.getAllClinicDto(), HttpStatus.OK);
    }

}
