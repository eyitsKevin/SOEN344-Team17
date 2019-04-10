package com.soen344.ubersante.services;

import com.soen344.ubersante.models.Clinic;
import com.soen344.ubersante.models.Doctor;
import com.soen344.ubersante.models.Nurse;
import com.soen344.ubersante.repositories.ClinicRepository;
import com.soen344.ubersante.repositories.DoctorRepository;
import com.soen344.ubersante.repositories.NurseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicService {

    @Autowired
    private ClinicRepository clinicRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private NurseRepository nurseRepository;

    public List<Clinic> getAllClinics() {
        return clinicRepository.findAll();
    }

    public Clinic getClinicById(long id) {
        return clinicRepository.getOne(id);
    }

    public List<Doctor> getDoctorByClinicId(long id) {
        return doctorRepository.findAllByClinicId(id);
    }

    public List<Nurse> getNurseByClinicId(long id) {
        return nurseRepository.findAllByClinicId(id);
    }


}