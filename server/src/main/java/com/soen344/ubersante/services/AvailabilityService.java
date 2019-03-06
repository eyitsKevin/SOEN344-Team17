package com.soen344.ubersante.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.soen344.ubersante.dto.AvailabilityDto;
import com.soen344.ubersante.exceptions.AvailabilityOverlapException;
import com.soen344.ubersante.exceptions.DateNotFoundException;
import com.soen344.ubersante.exceptions.InvalidAppointmentException;
import com.soen344.ubersante.exceptions.InvalidAvailabilityException;
import com.soen344.ubersante.models.Availability;
import com.soen344.ubersante.repositories.AvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.*;

@Service
public class AvailabilityService {

    @Autowired
    AvailabilityRepository availabilityRepository;

    private static ValidatorFactory validatorFactory;

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

    public List<AvailabilityDto> allAvailabilitiesByDoctorPermit(String doctorPermit) {
        List<AvailabilityDto> availabilityDtoList = new ArrayList<>();
        List<Availability> availabilities = availabilityRepository.findAllByDoctorPermitNumber(doctorPermit);

        for (Availability availability : availabilities) {
            availabilityDtoList.add(convertToAvailabilityDto(availability));
        }

        return availabilityDtoList;
    }

    @Transactional
    public AvailabilityDto addNewAvailability(AvailabilityDto availabilityDto)
            throws AvailabilityOverlapException, InvalidAvailabilityException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        validatorFactory.close();
        String doctorPermit = availabilityDto.getDoctorPermitNumber();
        LocalDateTime start = LocalDateTime.parse(availabilityDto.getStart(), formatter);
        LocalDateTime end = LocalDateTime.parse(availabilityDto.getEnd(), formatter);

        if (!availabilityRepository.findAllInDateRangeForDoctor(start, end, doctorPermit).isEmpty()) {
            throw new AvailabilityOverlapException("There exists an availability overlap conflict");
        }

        final Availability availability = new Availability();
        availability.setAppointmentType(availabilityDto.getAppointmentType());
        availability.setDoctorPermitNumber(doctorPermit);
        availability.setStartTime(start);
        availability.setEndTime(end);

        Set<ConstraintViolation<Availability>> violations = validator.validate(availability);

        if (!violations.isEmpty()) {
            throw new InvalidAvailabilityException("Invalid availability, failed backend validation");
        }

        return convertToAvailabilityDto(availabilityRepository.save(availability));
    }

    private AvailabilityDto convertToAvailabilityDto(@Valid Availability availability) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        AvailabilityDto dto = new AvailabilityDto();
        dto.setId(availability.getId());
        dto.setDoctorPermitNumber(availability.getDoctorPermitNumber());
        dto.setTitle(availability.getAppointmentType());
        dto.setDuration(availability.getAppointmentType().getDuration());
        dto.setStart(availability.getStartTime().format(formatter));
        dto.setEnd(availability.getEndTime().format(formatter));

        return dto;
    }
}
