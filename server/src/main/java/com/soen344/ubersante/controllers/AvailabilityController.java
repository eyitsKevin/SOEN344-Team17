package com.soen344.ubersante.controllers;

import com.soen344.ubersante.exceptions.DateNotFoundException;
import com.soen344.ubersante.exceptions.InvalidAppointmentException;
import com.soen344.ubersante.services.AvailabilityService;
import com.soen344.ubersante.validation.ValidPermitNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/availability")
public class AvailabilityController {

    @Autowired
    private AvailabilityService availabilityService;

    @RequestMapping(value = "/view/{availabilityType}/{month}", method = RequestMethod.GET)
    public ResponseEntity getAvailabilityByMonth(@PathVariable String month, @PathVariable String availabilityType) {
        try {
            return new ResponseEntity<>(availabilityService.getAvailabilityByMonth(month, availabilityType), HttpStatus.OK);
        } catch(DateNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch(InvalidAppointmentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch(NumberFormatException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/doctor/{permit}")
    public ResponseEntity getAllDoctorAvailabilities(@ValidPermitNumber @PathVariable String permit) {
        return new ResponseEntity<>(availabilityService.allAvailabilitiesByDoctorPermit(permit), HttpStatus.OK);
    }
}
