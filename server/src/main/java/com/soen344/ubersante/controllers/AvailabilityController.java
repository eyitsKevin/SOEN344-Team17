package com.soen344.ubersante.controllers;

import com.soen344.ubersante.dto.AvailabilityDto;
import com.soen344.ubersante.exceptions.AvailabilityOverlapException;
import com.soen344.ubersante.exceptions.DateNotFoundException;
import com.soen344.ubersante.exceptions.InvalidAppointmentException;
import com.soen344.ubersante.repositories.AvailabilityRepository;
import com.soen344.ubersante.services.AvailabilityService;
import com.soen344.ubersante.validation.ValidPermitNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/availability")
public class AvailabilityController {

    @Autowired
    private AvailabilityService availabilityService;

    @Autowired
    private AvailabilityRepository availabilityRepository;

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

    @PostMapping("/create")
    public ResponseEntity createNewAvailability(@Valid @RequestBody final AvailabilityDto availabilityDto) {

        try {
            return new ResponseEntity<>(availabilityService.addNewAvailability(availabilityDto), HttpStatus.CREATED);
        } catch (AvailabilityOverlapException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (InvalidAppointmentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAvailability(@PathVariable("id") long id) {
        availabilityRepository.deleteById(id);

        return new ResponseEntity<>("Availability has been deleted", HttpStatus.OK);
    }
}
