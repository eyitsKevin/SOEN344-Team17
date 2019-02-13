package com.soen344.ubersante.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HealthCardValidator implements ConstraintValidator<ValidHealthCard, String> {

    private Pattern pattern;
    private Matcher matcher;

    private static final String VALID_HEALTH_CARD = "^[A-Z,a-z]{4}\\d{8}$";

    public void initialize(final ValidHealthCard constraintAnnotation) {}

    public boolean isValid(String healthCard, ConstraintValidatorContext context) {
        return (validateHealthCard(healthCard));
    }

    private boolean validateHealthCard(String healthCard) {
        pattern = Pattern.compile(VALID_HEALTH_CARD);
        matcher = pattern.matcher(healthCard);

        return matcher.matches();
    }
}
