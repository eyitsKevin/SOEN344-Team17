package com.soen344.ubersante.services;

import java.util.List;

import com.soen344.ubersante.exceptions.DateNotFoundException;
import com.soen344.ubersante.exceptions.InvalidAppointmentException;
import com.soen344.ubersante.models.Availability;
import com.soen344.ubersante.repositories.AvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvailabilityService {

    @Autowired
    AvailabilityRepository availabilityRepository;

    public List<Availability> getAvailabilityByMonth(String month, String availabilityType) throws DateNotFoundException, InvalidAppointmentException, NumberFormatException{
         
        int monthVal = Integer.parseInt(month);
        String availType;
        if (monthVal <= 0 || monthVal > 12) {
            throw new DateNotFoundException("Invalid month value.");
        } 

        if (availabilityType.equalsIgnoreCase("walkin")) {
            availType = "0";
        } else if (availabilityType.equalsIgnoreCase("annual")) {
            availType = "1";
        } else {
            throw new InvalidAppointmentException("Invalid appointment type.");
        }

        return availabilityRepository.findAvailabilitiesByMonth(month, availType);
    }
}
