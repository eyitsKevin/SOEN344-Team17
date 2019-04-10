package com.soen344.ubersante.services;

import com.soen344.ubersante.models.Clinic;
import com.soen344.ubersante.repositories.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicService {
    
    @Autowired
    private ClinicRepository clinicRepository;

    public List<Clinic> getAllClinics() {
        return clinicRepository.findAll();
    }
}