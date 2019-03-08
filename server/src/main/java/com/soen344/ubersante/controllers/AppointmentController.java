package com.soen344.ubersante.controllers;

import com.soen344.ubersante.dto.PatientDetails;
import com.soen344.ubersante.services.AppointmentService;
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

    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public ResponseEntity getAllAppointmentFromPatient(@RequestBody PatientDetails patientDetails) {
        return new ResponseEntity<>(appointmentService.getAppointmentDetails(appointmentService.findAppointmentForPatient(patientDetails)), HttpStatus.OK);
    }
}
