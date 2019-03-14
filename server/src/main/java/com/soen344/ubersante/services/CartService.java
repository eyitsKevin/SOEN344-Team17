package com.soen344.ubersante.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.soen344.ubersante.dto.AvailabilityDto;
import com.soen344.ubersante.enums.AppointmentType;
import com.soen344.ubersante.exceptions.*;
import com.soen344.ubersante.dto.AvailabilityDetails;
import com.soen344.ubersante.dto.PatientDetails;
import com.soen344.ubersante.models.Appointment;
import com.soen344.ubersante.models.Availability;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.*;

import java.sql.Timestamp;

@Service
public class CartService {

    private HashMap<String, List<AvailabilityDetails>> patientsCart = new HashMap();

    public boolean saveAvailability(String healthCard, List<AvailabilityDetails> availabilities) {
        patientsCart.put(healthCard, availabilities);
        return true;
    }

    public List<AvailabilityDetails> retrieveCartAvailability(String healthCard) {
        return patientsCart.get(healthCard);
    }
}