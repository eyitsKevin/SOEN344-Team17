package com.soen344.ubersante;

import com.soen344.ubersante.models.Doctor;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.Assert.*;

public class DoctorValidationTest {

    @Test
    public void whenInvalidPermitNumber() {
        //TODO: permit number test validator
        assertTrue(true);
    }

    @Test
    public void whenValidPermitNumber() {
        //TODO: test permit number validator
        assertTrue(true);
    }

    private static ValidatorFactory validatorFactory;
    private static Validator validator;
    private Doctor doctor;

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
        doctor = new Doctor();
        doctor.setPermitNumber("1234567");
        doctor.setLastName("Kardashian");
        doctor.setFirstName("Kim");
        doctor.setSpecialty("FamilyDoctor");
        doctor.setCity("Montreal");
        doctor.setPassword("123456");

    }

    @Test
    public void shouldHaveNoViolations() {

        Set<ConstraintViolation<Doctor>> violations = validator.validate(doctor);

        assertTrue("There are no validator violations", violations.isEmpty());
    }

    @Test
    public void shouldDetectInvalidPermitNumber() {
        doctor.setPermitNumber("InvalidPermitNumber");

        Set<ConstraintViolation<Doctor>> violations = validator.validate(doctor);
        assertEquals(1, violations.size());

        ConstraintViolation<Doctor> violation = violations.iterator().next();
        assertEquals("Invalid Permit Number", violation.getMessage());
        assertEquals("permitNumber", violation.getPropertyPath().toString());
        assertEquals("InvalidPermitNumber", violation.getInvalidValue());
    }

    @Test
    public void shouldDetectBlankFields() {
        Set<ConstraintViolation<Doctor>> violations;
        doctor.setPermitNumber("");
        doctor.setLastName("");
        doctor.setFirstName("");
        doctor.setSpecialty("");
        doctor.setCity("");
        doctor.setPassword("");

        violations = validator.validate(doctor);
        assertEquals(6, violations.size());

    }
}
