package com.soen344.ubersante.users.controllers;

import com.soen344.ubersante.users.models.Patient;
import com.soen344.ubersante.users.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    PatientRepository repository;

    @GetMapping("/all")
    public List<Patient> getAllPatients() {
        System.out.println("Get all Patients...");

        List<Patient> patients = new ArrayList<>();
        repository.findAll().forEach(patients::add);

        return patients;
    }

    @PostMapping(value = "/create")
    public Patient createPatient(@RequestBody Patient patient) {
        System.out.println("Creating new Patient");

        Patient _patient = repository.save(new Patient(patient));
        return _patient;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable("id") long id) {
        System.out.println("Delete patient with ID = " + id+ "...");

        repository.deleteById(id);

        return new ResponseEntity<>("Client has been deleted", HttpStatus.OK);
    }
}
