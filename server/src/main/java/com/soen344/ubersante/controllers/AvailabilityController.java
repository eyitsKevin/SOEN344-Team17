package com.soen344.ubersante.controllers;

import com.soen344.ubersante.dto.DoctorLoginForm;
import com.soen344.ubersante.exceptions.DoctorNotFoundException;
import com.soen344.ubersante.exceptions.InvalidPasswordException;
import com.soen344.ubersante.models.Availability;
import com.soen344.ubersante.services.AvailabilityService;
import com.soen344.ubersante.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/availability")
public class AvailabilityController {

    @Autowired
    private AvailabilityService availabilityService;

    @GetMapping("/view")
    public ResponseEntity getAvailabilityByMonth(@RequestBody LocalDateTime date) {
        try {
            return new ResponseEntity<>(availabilityService.getAvailabilityByMonth(date), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>("Server Error.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
