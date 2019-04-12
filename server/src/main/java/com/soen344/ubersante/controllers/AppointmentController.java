package com.soen344.ubersante.controllers;

import java.util.HashMap;

import com.soen344.ubersante.dto.PatientDetails;
import com.soen344.ubersante.dto.UpdateAppointmentWrapper;
import com.soen344.ubersante.exceptions.DoctorNotFoundException;
import com.soen344.ubersante.exceptions.EmptyCartException;
import com.soen344.ubersante.exceptions.NoAppointmentException;
import com.soen344.ubersante.exceptions.PatientNotFoundException;
import com.soen344.ubersante.models.ClinicAvailabilities;
import com.soen344.ubersante.services.AppointmentService;
import com.soen344.ubersante.services.AvailabilityService;

import com.soen344.ubersante.validation.ValidPermitNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private AvailabilityService availabilityService;

    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public ResponseEntity getAllAppointmentFromPatient(@RequestBody PatientDetails patientDetails) {
        try {
            return new ResponseEntity<>(appointmentService.getAppointmentDetails(appointmentService.findAppointmentForPatient(patientDetails)), HttpStatus.OK);
        } catch (NoAppointmentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/cancel")
    @ResponseStatus(value = HttpStatus.OK)
    public void cancelAppointmentPatient(@RequestBody long id) {
        availabilityService.clinic = new HashMap<Long,ClinicAvailabilities>();
        appointmentService.cancelAppointmentforPatient(id);
    }

    @PostMapping("/update")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity updateAppointmentPatient(@RequestBody UpdateAppointmentWrapper details) {
        try {
            availabilityService.clinic = new HashMap<Long,ClinicAvailabilities>();
            appointmentService.cancelAppointmentforPatient(details.getAppointmentId());
            return new ResponseEntity<>(availabilityService.availabilityToAppointment(details.getPatient(), details.getCart()), HttpStatus.OK);
        } catch (EmptyCartException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (DoctorNotFoundException | PatientNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping("/doctor/{permit}")
    public ResponseEntity getAllAppointmentsForDoctor(@ValidPermitNumber @PathVariable String permit) {
        return new ResponseEntity<>(appointmentService.getAppointmentDetailsForDoctor(permit), HttpStatus.OK);
    }
}
