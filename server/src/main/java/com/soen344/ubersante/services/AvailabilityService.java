package com.soen344.ubersante.services;

import java.time.LocalDateTime;
import java.util.List;

import com.soen344.ubersante.dto.DoctorDetails;
import com.soen344.ubersante.dto.DoctorLoginForm;
import com.soen344.ubersante.exceptions.DoctorNotFoundException;
import com.soen344.ubersante.exceptions.InvalidPasswordException;
import com.soen344.ubersante.models.Availability;
import com.soen344.ubersante.models.Doctor;
import com.soen344.ubersante.repositories.AvailabilityRepository;
import com.soen344.ubersante.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvailabilityService {

    @Autowired
    AvailabilityRepository availabilityRepository;

    public List<Availability> getAvailabilityByMonth(LocalDateTime date) {
        return availabilityRepository.findByAllByMonth(date);
    }
}
