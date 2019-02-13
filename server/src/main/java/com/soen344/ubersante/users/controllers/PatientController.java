package com.soen344.ubersante.users.controllers;

import com.soen344.ubersante.users.dto.PatientDto;
import com.soen344.ubersante.users.models.Patient;
import com.soen344.ubersante.users.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    PatientRepository patientRepository;

    @GetMapping("/all")
    public List<Patient> getAllPatients() {
        System.out.println("Get all Patients...");

        List<Patient> patients = new ArrayList<>();
        patientRepository.findAll().forEach(patients::add);

        return patients;
    }

    @PostMapping("/registration")
    @ResponseBody
    public ResponseEntity<String> registerNewPatient(@Valid final PatientDto patientDto) {
        
    }
}
