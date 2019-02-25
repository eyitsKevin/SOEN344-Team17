package com.soen344.ubersante.model;

import com.soen344.ubersante.enums.AppointmentType;
import com.soen344.ubersante.models.Availability;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

public class AvailabilityValidationTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;
    private Availability availability;
    private LocalDate tomorrow;

    @BeforeClass
    public static void createValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterClass
    public static void close() {
        validatorFactory.close();
    }

    @Before
    public void setUp() {
        tomorrow = LocalDate.now().plusDays(1);
        availability = new Availability();
        availability.setId(1);
        availability.setDoctorPermitNumber("1234567");
    }

    @Test
    public void shouldHaveNoViolationsWalkIn() {
        LocalTime start = LocalTime.of(8, 0);
        availability.setAppointmentType(AppointmentType.WALK_IN);
        availability.setStartTime(LocalDateTime.of(tomorrow, start));
        availability.setEndTime(LocalDateTime.of(tomorrow, start.plusMinutes(20)));

        Set<ConstraintViolation<Availability>> violations = validator.validate(availability);

        assertTrue("There are no violations for valid walk in", violations.isEmpty());
    }

    @Test
    public void shouldHaveNoViolationsAnnualCheckup() {
        LocalTime start = LocalTime.of(8, 0);
        availability.setAppointmentType(AppointmentType.ANNUAL_CHECKUP);
        availability.setStartTime(LocalDateTime.of(tomorrow, start));
        availability.setEndTime(LocalDateTime.of(tomorrow, start.plusMinutes(60)));

        Set<ConstraintViolation<Availability>> violations = validator.validate(availability);

        assertTrue("There are no violations for valid annual checkup", violations.isEmpty());
    }

    @Test
    public void shouldDetectInvalidScheduleTimes() {
        for (int i = 1; i < 60; i++) {
            if (i == 20 || i == 40) continue;

            LocalTime start = LocalTime.of(8, i);
            availability.setAppointmentType(AppointmentType.WALK_IN);
            availability.setStartTime(LocalDateTime.of(tomorrow, start));
            availability.setEndTime(LocalDateTime.of(tomorrow, start.plusMinutes(20)));

            Set<ConstraintViolation<Availability>> violations = validator.validate(availability);
            assertEquals(2, violations.size());

            for (ConstraintViolation<Availability> violation : violations) {
                assertEquals("Invalid Time", violation.getMessage());
            }
        }
    }

    @Test
    public void shouldDetectInvalidWalkInDuration() {
        for (int i = 0; i < 120; i++) {
            if ((i % 20) == 0) continue;
            LocalTime start = LocalTime.of(8, 0);
            availability.setAppointmentType(AppointmentType.WALK_IN);
            availability.setStartTime(LocalDateTime.of(tomorrow, start));
            availability.setEndTime(LocalDateTime.of(tomorrow, start.plusMinutes(i)));

            Set<ConstraintViolation<Availability>> violations = validator.validate(availability);
            assertEquals("should detect both duration and time", 2, violations.size());
        }
    }

    @Test
    public void shouldDetectInvalidAnnualCheckupDuration() {
        for (int i = 0; i < 120; i++) {
            if ((i % 20) == 0) continue;
            LocalTime start = LocalTime.of(8, 0);
            availability.setAppointmentType(AppointmentType.ANNUAL_CHECKUP);
            availability.setStartTime(LocalDateTime.of(tomorrow, start));
            availability.setEndTime(LocalDateTime.of(tomorrow, start.plusMinutes(i)));

            Set<ConstraintViolation<Availability>> violations = validator.validate(availability);
            assertEquals("should detect both duration and time", 2, violations.size());
        }
    }
}
