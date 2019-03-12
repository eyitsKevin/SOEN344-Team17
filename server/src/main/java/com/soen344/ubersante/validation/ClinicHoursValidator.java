package com.soen344.ubersante.validation;

import com.soen344.ubersante.models.ClinicHours;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalTime;

public class ClinicHoursValidator implements ConstraintValidator<ValidClinicHours, ClinicHours> {

    public void initialize(final ValidClinicHours constraintAnnotation) {}

    public boolean isValid(ClinicHours clinicHours, ConstraintValidatorContext context) {
        return openBeforeClose(clinicHours.getOpen(), clinicHours.getClose());
    }

    private boolean openBeforeClose(LocalTime open, LocalTime close) {
        return open.isBefore(close);
    }
}
