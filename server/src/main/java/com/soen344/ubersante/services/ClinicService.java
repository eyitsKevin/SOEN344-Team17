package com.soen344.ubersante.services;

import com.soen344.ubersante.dto.ClinicDto;
import com.soen344.ubersante.dto.DoctorDetails;
import com.soen344.ubersante.dto.NurseDetails;
import com.soen344.ubersante.models.Clinic;
import com.soen344.ubersante.models.Doctor;
import com.soen344.ubersante.models.Nurse;
import com.soen344.ubersante.repositories.ClinicRepository;
import com.soen344.ubersante.repositories.DoctorRepository;
import com.soen344.ubersante.repositories.NurseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public ClinicDto buildClinicDto(long id) {
        Clinic clinic = getClinicById(id);
        List<DoctorDetails> doctorDetailsList = getDoctorByClinicId(id);
        List<NurseDetails> nurseDetailsList = getNurseByClinicId(id);

        return new ClinicDto(clinic, doctorDetailsList, nurseDetailsList);
    }

    private List<DoctorDetails> getDoctorByClinicId(long id) {
        List<DoctorDetails> doctorDetailsList = new ArrayList<>();
        for (Doctor doctor : doctorRepository.findAllByClinicId(id)) {
            doctorDetailsList.add(new DoctorDetails(doctor));
        }
        return doctorDetailsList;
    }

    private List<NurseDetails> getNurseByClinicId(long id) {
        List<NurseDetails> nurseDetails = new ArrayList<>();
        for (Nurse nurse : nurseRepository.findAllByClinicId(id)) {
            nurseDetails.add(new NurseDetails(nurse));
        }
        return nurseDetails;
    }


}