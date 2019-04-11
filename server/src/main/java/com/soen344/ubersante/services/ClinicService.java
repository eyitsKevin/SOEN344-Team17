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

@Service
public class ClinicService {

    @Autowired
    private ClinicRepository clinicRepository;

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
}
