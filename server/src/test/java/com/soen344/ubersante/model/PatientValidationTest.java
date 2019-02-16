package com.soen344.ubersante.model;

import static org.junit.Assert.*;

import com.soen344.ubersante.models.Patient;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientValidationTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;
    private Patient patient;

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
        patient = new Patient();
        patient.setHealthCard("JACM00000000");
        patient.setFirstName("James");
        patient.setLastName("Jackson");
        patient.setGender("Male");
        patient.setBirthday("1986-12-08");
        patient.setPhone("5142222222");
        patient.setEmail("test@notreal.com");
        patient.setAddress("H937");
        patient.setPassword("123456");

    }

    @Test
    public void shouldHaveNoViolations() {

        Set<ConstraintViolation<Patient>> violations = validator.validate(patient);

        assertTrue("There are no validator violations", violations.isEmpty());
    }

    @Test
    public void shouldDetectInvalidHealthCard() {
        patient.setHealthCard("InvalidHealthCard");

        Set<ConstraintViolation<Patient>> violations = validator.validate(patient);
        assertEquals(1, violations.size());

        ConstraintViolation<Patient> violation = violations.iterator().next();
        assertEquals("Invalid Health Card", violation.getMessage());
        assertEquals("healthCard", violation.getPropertyPath().toString());
        assertEquals("InvalidHealthCard", violation.getInvalidValue());
    }

    @Test
    public void shouldDetectInvalidEmail() {
        Set<ConstraintViolation<Patient>> violations;
        ConstraintViolation<Patient> violation;

        patient.setEmail("notvalid");
        violations = validator.validate(patient);
        assertEquals(1, violations.size());
        violation = violations.iterator().next();
        assertEquals("Invalid Email", violation.getMessage());
        assertEquals("email", violation.getPropertyPath().toString());
        assertEquals("notvalid", violation.getInvalidValue());

        patient.setEmail("");
        violations = validator.validate(patient);
        assertEquals(1, violations.size());
        violation = violations.iterator().next();
        assertEquals("Invalid Email", violation.getMessage());
        assertEquals("email", violation.getPropertyPath().toString());
        assertEquals("", violation.getInvalidValue());

        patient.setEmail("@");
        violations = validator.validate(patient);
        assertEquals(1, violations.size());
        violation = violations.iterator().next();
        assertEquals("Invalid Email", violation.getMessage());
        assertEquals("email", violation.getPropertyPath().toString());
        assertEquals("@", violation.getInvalidValue());

        patient.setEmail("abc@ac");
        violations = validator.validate(patient);
        assertEquals(1, violations.size());
        violation = violations.iterator().next();
        assertEquals("Invalid Email", violation.getMessage());
        assertEquals("email", violation.getPropertyPath().toString());
        assertEquals("abc@ac", violation.getInvalidValue());

        patient.setEmail("abc@test.c");
        violations = validator.validate(patient);
        assertEquals(1, violations.size());
        violation = violations.iterator().next();
        assertEquals("Invalid Email", violation.getMessage());
        assertEquals("email", violation.getPropertyPath().toString());
        assertEquals("abc@test.c", violation.getInvalidValue());
    }

    @Test
    public void shouldDetectBlankFields() {
        Set<ConstraintViolation<Patient>> violations;
        patient.setHealthCard("");
        patient.setFirstName("");
        patient.setLastName("");
        patient.setGender("");
        patient.setBirthday("");
        patient.setPhone("");
        patient.setEmail("");
        patient.setAddress("");
        patient.setPassword("");

        violations = validator.validate(patient);
        assertEquals(9, violations.size());

    }




}
