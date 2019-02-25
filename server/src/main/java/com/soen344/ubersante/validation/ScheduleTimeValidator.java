package com.soen344.ubersante.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

public class ScheduleTimeValidator implements ConstraintValidator<ValidScheduleTime, LocalDateTime> {

    public void initialize(final ValidAccessId constraintAnnotation) {}

    public boolean isValid(LocalDateTime startTime, ConstraintValidatorContext context) {
        return (validateStartTime(startTime));
    }

    /**
     * Time blocks are in 20 minute intervals, a valid start time begins at the 0 minute, 20 minute and 40 minute mark
     */
    private boolean validateStartTime(LocalDateTime startTime) {
        int nano = startTime.getNano();
        int second = startTime.getSecond();
        int minute = startTime.getMinute();

        if (nano != 0) {
            return false;
        }

        if (second != 0) {
            return false;
        }

        return minute == 0 || minute == 20 || minute == 40;
    }
}
