package com.soen344.ubersante.users.controllers;

import com.soen344.ubersante.users.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.soen344.ubersante.users.models.Doctor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    DoctorRepository repository;

    @GetMapping("/all")
    public List<Doctor> getAllDoctors() {
        System.out.println("Get all Doctors...");

        List<Doctor> doctors = new ArrayList<>();
        repository.findAll().forEach(doctors::add);

        return doctors;
    }

    @PostMapping(value = "/create")
    public Doctor postDoctor(@RequestBody Doctor doctor) {
        System.out.println("Create Doctor = " + doctor + "...");

        Doctor _doctor = repository.save(new Doctor(doctor));
        return _doctor;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable("id") long id) {
        System.out.println("Delete Doctor with ID = " + id + "...");

        repository.deleteById(id);

        return new ResponseEntity<>("Doctor has been deleted!", HttpStatus.OK);
    }
}
