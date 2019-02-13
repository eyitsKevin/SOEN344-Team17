package com.soen344.ubersante.users.controllers;

import com.soen344.ubersante.users.models.Nurse;
import com.soen344.ubersante.users.repositories.NurseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/nurses")
public class NurseController {

    @Autowired
    NurseRepository repository;

    @GetMapping("/all")
    public List<Nurse> getAllNurses() {
        System.out.println("Get all Doctors...");

        List<Nurse> nurses = new ArrayList<>();
        repository.findAll().forEach(nurses::add);

        return nurses;
    }
}
