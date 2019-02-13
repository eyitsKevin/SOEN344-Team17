package com.soen344.ubersante.model;

import static org.junit.Assert.*;

import com.soen344.ubersante.models.Patient;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class PatientValidationTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeClass
    public static void createValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterClass
    public static void close() {
        validatorFactory.close();
    }

    @Test
    public void shouldHaveNoViolations() {
        Patient patient = new Patient();
        patient.setHealthCard("JACM00000000");
        patient.setFirstName("James");
        patient.setLastName("Jackson");
        patient.setGender("Male");
        patient.setBirthday("1986-12-08");
        patient.setPhone("5142222222");
        patient.setEmail("test@notreal.com");
        patient.setAddress("H937");
        patient.setPassword("123456");

        Set<ConstraintViolation<Patient>> violations = validator.validate(patient);

        assertTrue("There are no validator violations", violations.isEmpty());
    }




}
