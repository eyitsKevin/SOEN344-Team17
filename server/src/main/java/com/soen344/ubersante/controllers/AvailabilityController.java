package com.soen344.ubersante.controllers;

import com.soen344.ubersante.exceptions.DateNotFoundException;
import com.soen344.ubersante.services.AvailabilityService;
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

    @RequestMapping(value = "/view/{month}", method = RequestMethod.GET)
    public ResponseEntity getAvailabilityByMonth(@PathVariable String month) {
        int monthVal = Integer.parseInt(month);
        try {
            if (0 < monthVal && monthVal <= 12) {
                return new ResponseEntity<>(availabilityService.getAvailabilityByMonth(month), HttpStatus.OK);
            } else {
                throw new DateNotFoundException();
            }
        } catch(DateNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
