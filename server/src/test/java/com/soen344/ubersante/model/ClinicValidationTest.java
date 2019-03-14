package com.soen344.ubersante.model;

import com.soen344.ubersante.models.Clinic;
import com.soen344.ubersante.models.ClinicHours;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalTime;
import java.util.Set;

public class ClinicValidationTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;
    private Clinic clinic;

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
        clinic = new Clinic();
        clinic.setId(1);
        clinic.setName("name");
        clinic.setRooms(5);
        clinic.setClinicHours(new ClinicHours(LocalTime.of(8, 0), LocalTime.of(8, 20)));
    }

    @Test
    public void shouldHaveNoViolations() {

        Set<ConstraintViolation<Clinic>> violations = validator.validate(clinic);

        assertTrue("No Violations for Clinic", violations.isEmpty());
    }

    @Test
    public void shouldDetectBlankName() {
        clinic.setName("");

        Set<ConstraintViolation<Clinic>> violations = validator.validate(clinic);
        assertEquals(1, violations.size());

        for (ConstraintViolation<Clinic> violation : violations) {
            assertEquals("must not be blank", violation.getMessage());
        }
    }

    @Test
    public void shouldDetectNegativeRoom() {
        clinic.setRooms(0);

        Set<ConstraintViolation<Clinic>> violations = validator.validate(clinic);
        assertEquals(1, violations.size());

        for (ConstraintViolation<Clinic> violation : violations) {
            assertEquals("must be greater than 0", violation.getMessage());
        }
    }

    @Test
    public void shouldDetectInvalidClinicHours() {
        clinic.setClinicHours(new ClinicHours(LocalTime.of(8, 20), LocalTime.of(8, 0)));

        Set<ConstraintViolation<Clinic>> violations = validator.validate(clinic);
        assertEquals(1, violations.size());

        for (ConstraintViolation<Clinic> violation : violations) {
            assertEquals("invalid clinic hours", violation.getMessage());
        }
    }
}
