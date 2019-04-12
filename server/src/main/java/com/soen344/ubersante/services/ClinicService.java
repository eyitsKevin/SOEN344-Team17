package com.soen344.ubersante.services;

import com.soen344.ubersante.dto.ClinicRegistrationForm;
import com.soen344.ubersante.models.Clinic;
import com.soen344.ubersante.models.ClinicHours;
import com.soen344.ubersante.repositories.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.List;

import com.soen344.ubersante.dto.ClinicDto;
import com.soen344.ubersante.dto.DoctorDetails;
import com.soen344.ubersante.dto.NurseDetails;
import com.soen344.ubersante.models.Doctor;
import com.soen344.ubersante.models.Nurse;
import com.soen344.ubersante.repositories.DoctorRepository;
import com.soen344.ubersante.repositories.NurseRepository;
import java.util.ArrayList;

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

    @Transactional
    public Clinic registerNewClinic(ClinicRegistrationForm clinicRegistrationForm) {
        LocalTime open = LocalTime.parse(clinicRegistrationForm.getOpenTime());
        LocalTime close = LocalTime.parse(clinicRegistrationForm.getCloseTime());
        final Clinic clinic = new Clinic();

        clinic.setName(clinicRegistrationForm.getName());
        clinic.setRooms(clinicRegistrationForm.getRooms());
        clinic.setClinicHours(new ClinicHours(open, close));

        return clinicRepository.save(clinic);
    }

    public Clinic getClinicById(long id) {
        return clinicRepository.getOne(id);
    }

    public List<ClinicDto> getAllClinicDto() {
        List<Clinic> clinicList = getAllClinics();
        List<ClinicDto> clinicDtos = new ArrayList<>();

        for (Clinic clinic : clinicList) {
            clinicDtos.add(buildClinicDto(clinic.getId()));
        }
        return  clinicDtos;
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
