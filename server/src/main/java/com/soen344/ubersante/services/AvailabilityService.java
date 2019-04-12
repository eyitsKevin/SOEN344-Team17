package com.soen344.ubersante.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    
    public Map<Long, ClinicAvailabilities> clinic = new HashMap<Long,ClinicAvailabilities>();

    public List<Availability> getAvailabilityByMonth(String month, String availabilityType, String clinicId) throws DateNotFoundException, InvalidAppointmentException, NumberFormatException {
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
        if(clinic.get(Long.parseLong(clinicId)) == null){
        clinic.put(Long.parseLong(clinicId), new ClinicAvailabilities());
        }

        if (availType == "0") {
            if (clinic.get(Long.parseLong(clinicId)).walkin.get(monthVal).hasChanged) {
                clinic.get(Long.parseLong(clinicId)).walkin.get(monthVal).listAvail = availabilityRepository.findAvailabilitiesByMonth(month, availType, clinicId);
                clinic.get(Long.parseLong(clinicId)).walkin.get(monthVal).hasChanged = false;
            } else {
                return clinic.get(Long.parseLong(clinicId)).walkin.get(monthVal).listAvail;
            }
        } else if (availType == "1") {
            if (clinic.get(Long.parseLong(clinicId)).walkin.get(monthVal).hasChanged) {
                clinic.get(Long.parseLong(clinicId)).walkin.get(monthVal).listAvail = availabilityRepository.findAvailabilitiesByMonth(month, availType, clinicId);
                clinic.get(Long.parseLong(clinicId)).walkin.get(monthVal).hasChanged = false;
             } else {
                 return clinic.get(Long.parseLong(clinicId)).walkin.get(monthVal).listAvail;
             }
        }   
        return clinic.get(Long.parseLong(clinicId)).walkin.get(monthVal).listAvail;
    }

    public void addAppointmentToTable(AvailabilityDetails availability, Appointment appointment) {
        availabilityRepository.addAppointmentToAvailability(availability.getId(), appointment.getId());
    }

    public boolean availabilityToAppointment(PatientDetails patientDetails, List<AvailabilityDetails> availabilityDetailsCart) throws PatientNotFoundException, EmptyCartException, DoctorNotFoundException, AvailabilityDoesNotExistException {
        LocalDateTime ldt = LocalDateTime.now();
        Timestamp ts = Timestamp.valueOf(ldt);
        Patient patient = patientRepository.findByHealthCard(patientDetails.getHealthCard());

        if (patient == null) {
            throw new PatientNotFoundException("Patient not found in " + this);
        }

        if (availabilityDetailsCart.size() <= 0) {
            throw new EmptyCartException("Nothing in the cart");
        }

        if (annualCheckupOverlap(availabilityDetailsCart, patient.getId())) {
            throw new AnnualCheckupOverlapException("Annual checkups must be scheduled 1 year apart");
        }

        for (AvailabilityDetails details : availabilityDetailsCart) {

            if (availabilityRepository.checkIfAvailabilityExist(details.getId()).size() == 0) {
                throw new AvailabilityDoesNotExistException("Availability not found for " + details.getStartTime());
            }

            if (walkInCheckupOverlap(patient, details, availabilityDetailsCart)) {
                throw new WalkInOverlapException(details.getAppointmentType() + " appointment with " + details.getDoctorPermitNumber() + ", tried to book an appointment that is conflicting");
            }

            if (doctorRepository.findByPermitNumber(details.getDoctorPermitNumber()) == null) {
                throw new DoctorNotFoundException("Doctor not found in " + this);
            }

            if (noRoomsAvailable(details)) {
                throw new InvalidAppointmentException("No rooms available between " + details.getStartTime() + " and " + details.getEndTime());
            }

            Appointment appointment = new Appointment(
                    patient,
                    doctorRepository.findByPermitNumber(details.getDoctorPermitNumber()),
                    patient.getFirstName() + " " + patient.getLastName(),
                    details.getAppointmentType(),
                    LocalDateTime.parse(details.getStartTime()),
                    LocalDateTime.parse(details.getEndTime()),
                    ts,
                    clinicRepository.getOne(details.getClinic().getId())
                    );
                    String month = LocalDateTime.parse(details.getStartTime()).getMonth().toString();
                    int monthVal = Integer.parseInt(month);
    
                    clinicRepository.getOne(details.getClinic().getId());
            appointmentRepository.save(appointment);
            addAppointmentToTable(details, appointment);

            // update the map 
            if(details.getAppointmentType().toString() == "WALK_IN"){
                clinic.get(details.getClinic().getId()).walkin.get(monthVal).hasChanged = true;
            } else {
                clinic.get(details.getClinic().getId()).walkin.get(monthVal).hasChanged = true;
            }
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
        availability.setClinic(clinic);

        if (!validAvailability(availability)) {
            throw new InvalidAvailabilityException("Invalid availability, failed backend validation");
        }

        // check if the map is initialized or not
        if(this.clinic.get(clinic.getId()) == null){
            this.clinic.put(clinic.getId(), new ClinicAvailabilities());
        }
        
        // update the map
        int monthVal = start.getMonth().getValue();
        if(availability.getAppointmentType().toString() == "WALK_IN"){
            this.clinic.get(clinic.getId()).walkin.get(monthVal).hasChanged = true;
        } else {
            this.clinic.get(clinic.getId()).walkin.get(monthVal).hasChanged = true;
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
        availability.setClinic(clinic);

        if (!validAvailability(availability)) {
            throw new InvalidAvailabilityException("Invalid availability, failed backend validation");
        }


        // check if the map is initialized or not
        if(this.clinic.get(clinic.getId()) == null){
            this.clinic.put(clinic.getId(), new ClinicAvailabilities());
        }

        // update the map
        int monthVal = start.getMonth().getValue();
        if(availability.getAppointmentType().toString() == "WALK_IN"){
            this.clinic.get(clinic.getId()).walkin.get(monthVal).hasChanged = true;
        } else {
            this.clinic.get(clinic.getId()).walkin.get(monthVal).hasChanged = true;
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
        boolean startBeforeOpen = clinic.getOpen().isAfter(start.toLocalTime().minusHours(4));
        boolean endAfterClose = clinic.getClose().isBefore(end.toLocalTime().minusHours(4));

        return startBeforeOpen || endAfterClose;
    }

    private boolean walkInCheckupOverlap(Patient patient, AvailabilityDetails availabilityDetails, List<AvailabilityDetails> cart) {
        List<Appointment> walkInAppointment = appointmentRepository.findAllWalkInBookingsForPatient(patient.getId());

        for (Appointment appointment : walkInAppointment) {
            if (availabilityDetails.getStartTime().equalsIgnoreCase(appointment.getTime().toString())) {
                return true;
            }
        }

        for (int i = 0; i < cart.size() ; i++) {
            for (int j = 1; j < cart.size(); j++) {
                if (cart.get(i).getStartTime().equals(cart.get(j).getStartTime())) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean noRoomsAvailable(AvailabilityDetails potentialAppointment) {
        // TEMPORARY HARD CODE UNTIL RELEASE 2
        int roomCapacity = 5;

        LocalDateTime start = LocalDateTime.parse(potentialAppointment.getStartTime());
        LocalDateTime end = LocalDateTime.parse(potentialAppointment.getEndTime());

        List<Appointment> appointments = appointmentRepository.findAllAppointmentsBetween(start, end);

        return appointments.size() >= roomCapacity;
    }
}