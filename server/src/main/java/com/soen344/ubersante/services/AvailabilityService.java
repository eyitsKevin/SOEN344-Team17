package com.soen344.ubersante.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.soen344.ubersante.dto.AvailabilityDto;
import com.soen344.ubersante.enums.AppointmentType;
import com.soen344.ubersante.exceptions.*;
import com.soen344.ubersante.dto.AvailabilityDetails;
import com.soen344.ubersante.dto.PatientDetails;
import com.soen344.ubersante.models.*;
import com.soen344.ubersante.repositories.*;
import com.soen344.ubersante.validation.ValidCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.*;

import java.sql.Timestamp;

@Service
public class AvailabilityService {

    private static ValidatorFactory validatorFactory;

    @Autowired
    AvailabilityRepository availabilityRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    ClinicRepository clinicRepository;

    public List<Availability> getAvailabilityByMonth(String month, String availabilityType) throws DateNotFoundException, InvalidAppointmentException, NumberFormatException {

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

    public void addAppointmentToTable(AvailabilityDetails availability, Appointment appointment) {
        availabilityRepository.addAppointmentToAvailability(availability.getId() ,appointment.getId());
    }

    public boolean availabilityToAppointment(PatientDetails patient, List<AvailabilityDetails> availabilityDetailsCart) throws PatientNotFoundException, EmptyCartException, DoctorNotFoundException {
        LocalDateTime ldt = LocalDateTime.now();
        Timestamp ts = Timestamp.valueOf(ldt);

        if (patientRepository.findByHealthCard(patient.getHealthCard()) == null) {
            throw new PatientNotFoundException("Patient not found in " + this);
        }

        if (availabilityDetailsCart.size() <= 0) {
            throw new EmptyCartException("Nothing in the cart");
        }

        if (annualCheckupOverlap(availabilityDetailsCart, patientRepository.findByHealthCard(patient.getHealthCard()).getId())) {
            throw new AnnualCheckupOverlapException("Annual checkups must be scheduled 1 year apart");
        }

        for (AvailabilityDetails details : availabilityDetailsCart) {

            if (doctorRepository.findByPermitNumber(details.getDoctorPermitNumber()) == null) {
                throw new DoctorNotFoundException("Doctor not found in " + this);
            }

            Appointment appointment = new Appointment(
                    patientRepository.findByHealthCard(patient.getHealthCard()),
                    doctorRepository.findByPermitNumber(details.getDoctorPermitNumber()),
                    patient.getFirstName() + " " + patient.getLastName(),
                    details.getAppointmentType(),
                    LocalDateTime.parse(details.getStartTime()),
                    LocalDateTime.parse(details.getEndTime()),
                    ts);
            appointmentRepository.save(appointment);
            addAppointmentToTable(details, appointment);
        }
        return true;
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
        LocalDateTime start = LocalDateTime.parse(availabilityDto.getStart(), DateTimeFormatter.RFC_1123_DATE_TIME);
        LocalDateTime end = LocalDateTime.parse(availabilityDto.getEnd(), DateTimeFormatter.RFC_1123_DATE_TIME);
        String doctorPermit = availabilityDto.getDoctorPermitNumber();
        Clinic clinic = doctorRepository.findByPermitNumber(doctorPermit).getClinic();

        if (!availabilityRepository.findAllInDateRangeForDoctor(start, end, doctorPermit).isEmpty()) {
            throw new AvailabilityOverlapException("There exists an availability overlap conflict");
        }

        if (outsideClinicHours(start, end, clinic.getClinicHours())) {
            throw new InvalidAvailabilityException("Outside available clinic hours");
        }

        final Availability availability = new Availability();
        availability.setAppointmentType(availabilityDto.getAppointmentType());
        availability.setStartTime(start);
        availability.setEndTime(end);
        availability.setDoctorPermitNumber(doctorPermit);

        if (!validAvailability(availability)) {
            throw new InvalidAvailabilityException("Invalid availability, failed backend validation");
        }

        return convertToAvailabilityDto(availabilityRepository.save(availability));
    }

    @Transactional
    public AvailabilityDto modifyAvailability(AvailabilityDto availabilityDto)
            throws AvailabilityOverlapException, InvalidAvailabilityException {
        Optional<Availability> availabilityData = availabilityRepository.findById(availabilityDto.getId());

        if (!availabilityData.isPresent()) {
            throw new AvailabilityDoesNotExistException("Availability does not exist");
        }

        Availability availability = availabilityData.get();

        String doctorPermit = availabilityDto.getDoctorPermitNumber();
        LocalDateTime start = LocalDateTime.parse(availabilityDto.getStart(), DateTimeFormatter.RFC_1123_DATE_TIME);
        LocalDateTime end = LocalDateTime.parse(availabilityDto.getEnd(), DateTimeFormatter.RFC_1123_DATE_TIME);
        Clinic clinic = doctorRepository.findByPermitNumber(doctorPermit).getClinic();

        if (outsideClinicHours(start, end, clinic.getClinicHours())) {
            throw new InvalidAvailabilityException("Outside available clinic hours");
        }

        List<Availability> availabilityOverlap = availabilityRepository.findAllInDateRangeForDoctor(start, end, doctorPermit);
        availabilityOverlap.remove(availability);

        if (!availabilityOverlap.isEmpty()) {
            throw new AvailabilityOverlapException("There exists an availability overlap conflict");
        }

        availability.setAppointmentType(availabilityDto.getAppointmentType());
        availability.setStartTime(start);
        availability.setEndTime(end);

        if (!validAvailability(availability)) {
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

    private Boolean validAvailability(Availability availability) {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        validatorFactory.close();

        Set<ConstraintViolation<Availability>> violations = validator.validate(availability);

        return violations.isEmpty();
    }

    private boolean annualCheckupOverlap(@ValidCart List<AvailabilityDetails> cart, long patientId) {
        List<Appointment> annualAppointments = appointmentRepository.findAllAnnualBookingsForPatient(patientId);
        List<LocalDateTime> annualDates = new ArrayList<>();

        for (AvailabilityDetails details : cart) {
            if (details.getAppointmentType() == AppointmentType.ANNUAL_CHECKUP) {
                annualDates.add(LocalDateTime.parse(details.getStartTime()));
            }
        }

        for (Appointment appointment : annualAppointments) {
            LocalDateTime time = LocalDateTime.of(appointment.getDate().toLocalDate(), appointment.getTime().toLocalTime());

            for (LocalDateTime cartTime : annualDates) {
                if (withinOneYear(time, cartTime)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean withinOneYear(LocalDateTime t1, LocalDateTime t2) {
        Duration duration = Duration.between(t1, t2).abs();
        Duration oneYear = Duration.ofDays(365);

        return duration.compareTo(oneYear) < 0;
    }

    private boolean outsideClinicHours(LocalDateTime start, LocalDateTime end, ClinicHours clinic) {

        if (clinic.getOpen().isAfter(start.toLocalTime())) {
            return true;
        }

        if (clinic.getClose().isBefore(end.toLocalTime())) {
            return true;
        }

        return false;
    }
}