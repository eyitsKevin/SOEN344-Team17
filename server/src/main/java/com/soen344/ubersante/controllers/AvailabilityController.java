package com.soen344.ubersante.controllers;

import com.soen344.ubersante.dto.DoctorLoginForm;
import com.soen344.ubersante.exceptions.DoctorNotFoundException;
import com.soen344.ubersante.exceptions.InvalidPasswordException;
import com.soen344.ubersante.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/availability")
public class AvailabilityController {
    
}
