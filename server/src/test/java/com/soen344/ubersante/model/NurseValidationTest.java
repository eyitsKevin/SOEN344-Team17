package com.soen344.ubersante.model;

import static org.junit.Assert.*;

import com.soen344.ubersante.models.Nurse;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class NurseValidationTest {
    private static ValidatorFactory validatorFactory;
    private static Validator validator;
    private Nurse nurse;

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
        nurse = new Nurse();
        nurse.setAccessId("DOL96315");
        nurse.setPassword("12345");
        nurse.setFirstName("testname");
        nurse.setLastName("lastname");
    }

    @Test
    public void shouldHaveNoViolations() {

        Set<ConstraintViolation<Nurse>> violations = validator.validate(nurse);

        assertTrue("There are no validator violations", violations.isEmpty());
    }

    @Test
    public void shouldDetectBlankFields() {
        Set<ConstraintViolation<Nurse>> violations;
        nurse.setAccessId("");
        nurse.setPassword("");
        nurse.setFirstName("");
        nurse.setLastName("");

        violations = validator.validate(nurse);
        assertEquals("Detect all blank fields", 4, violations.size());
    }

    @Test
    public void shouldDetectInvalidAccessId() {
        Set<ConstraintViolation<Nurse>> violations;
        ConstraintViolation<Nurse> violation;

        nurse.setAccessId("notvalid");
        violations = validator.validate(nurse);
        assertEquals(1, violations.size());
        violation = violations.iterator().next();
        assertEquals("Invalid access ID", violation.getMessage());
        assertEquals("accessId", violation.getPropertyPath().toString());
        assertEquals("notvalid", violation.getInvalidValue());

        nurse.setAccessId("DOLL1234");
        violations = validator.validate(nurse);
        assertEquals(1, violations.size());
        violation = violations.iterator().next();
        assertEquals("Invalid access ID", violation.getMessage());
        assertEquals("accessId", violation.getPropertyPath().toString());
        assertEquals("DOLL1234", violation.getInvalidValue());

        nurse.setAccessId("DO11234");
        violations = validator.validate(nurse);
        assertEquals(1, violations.size());
        violation = violations.iterator().next();
        assertEquals("Invalid access ID", violation.getMessage());
        assertEquals("accessId", violation.getPropertyPath().toString());
        assertEquals("DO11234", violation.getInvalidValue());

        nurse.setAccessId("13212345");
        violations = validator.validate(nurse);
        assertEquals(1, violations.size());
        violation = violations.iterator().next();
        assertEquals("Invalid access ID", violation.getMessage());
        assertEquals("accessId", violation.getPropertyPath().toString());
        assertEquals("13212345", violation.getInvalidValue());
    }


}
