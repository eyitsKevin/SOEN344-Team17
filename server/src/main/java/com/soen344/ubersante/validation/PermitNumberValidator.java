package com.soen344.ubersante.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PermitNumberValidator implements ConstraintValidator<ValidPermitNumber, String> {

    private Pattern pattern;
    private Matcher matcher;

    private static final String VALID_PERMIT_NUMBER = "^[0-9]{7}$";

    public void initialize(final ValidPermitNumber constraintAnnotation) {}

    public boolean isValid(String permitNumber, ConstraintValidatorContext context) {
        return (validatePermitNumber(permitNumber));
    }

    private boolean validatePermitNumber(String permitNumber) {
        pattern = Pattern.compile(VALID_PERMIT_NUMBER);
        matcher = pattern.matcher(permitNumber);

        return matcher.matches();
    }

}
