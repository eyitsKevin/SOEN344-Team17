package com.soen344.ubersante.validation;

import com.soen344.ubersante.dto.AvailabilityDetails;
import com.soen344.ubersante.enums.AppointmentType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CartValidator implements ConstraintValidator<ValidCart, List<AvailabilityDetails>> {

    public void initialize(final ValidCart constraintAnnotation) {}

    public boolean isValid(List<AvailabilityDetails> cart, ConstraintValidatorContext context) {
        return (isValidCart(cart));
    }

    private boolean isValidCart(List<AvailabilityDetails> cart) {
        List<LocalDateTime> annualDates = new ArrayList<>();

        if (cart.isEmpty()) {
            return false;
        }

        for (AvailabilityDetails details : cart) {
            if (details.getAppointmentType() == AppointmentType.ANNUAL_CHECKUP) {
                annualDates.add(LocalDateTime.parse(details.getStartTime()));
            }
        }

        for (int i = 0; i < annualDates.size(); i++) {
            LocalDateTime t1 = annualDates.get(i);
            for (int j = i+1; j < annualDates.size(); j++) {
                if (withinOneYear(t1, annualDates.get(j))) {
                    return false;
                }
            }

        }

        return true;
    }

    private boolean withinOneYear(LocalDateTime t1, LocalDateTime t2) {
        Duration duration = Duration.between(t1, t2).abs();
        Duration oneYear = Duration.ofDays(365);

        return duration.compareTo(oneYear) < 0;
    }
}
