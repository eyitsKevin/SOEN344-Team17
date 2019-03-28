package com.soen344.ubersante.controllers;

import com.soen344.ubersante.dto.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/clinics")
public class ClinicController {

    @GetMapping("/")
    public ResponseEntity viewClinics() {

        return new ResponseEntity<>(new Response("Clinic"), HttpStatus.CREATED);
    }
}
