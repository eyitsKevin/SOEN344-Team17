package com.soen344.ubersante.services;

import java.util.List;

import com.soen344.ubersante.exceptions.DateNotFoundException;
import com.soen344.ubersante.models.Availability;
import com.soen344.ubersante.repositories.AvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvailabilityService {

    @Autowired
    AvailabilityRepository availabilityRepository;

    public List<Availability> getAvailabilityByMonth(String month, String availabilityType) {

        return availabilityRepository.findAvailabilitiesByMonth(month, availabilityType);
    }
}
