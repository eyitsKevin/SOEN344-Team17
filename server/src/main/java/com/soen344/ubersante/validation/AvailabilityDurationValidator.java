package com.soen344.ubersante.validation;

import com.soen344.ubersante.models.Availability;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

public class AvailabilityDurationValidator implements ConstraintValidator<ValidAvailabilityDuration, Availability> {

    @Override
    public void initialize(ValidAvailabilityDuration constraintAnnotation) {
    }

    @Override
    public boolean isValid(Availability availability, ConstraintValidatorContext context){
        int validDuration = availability.getAppointmentType().getDuration();
        LocalDateTime expectedEndTime = availability.getStartTime().plusMinutes(validDuration);

        return expectedEndTime.isEqual(availability.getEndTime());
    }
}
