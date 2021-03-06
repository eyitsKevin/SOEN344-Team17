package com.soen344.ubersante.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccessIdValidator implements ConstraintValidator<ValidAccessId, String> {

    private static final String VALID_ACCESS_ID = "^[a-zA-Z]{3}[0-9]{5}$";

    public void initialize(final ValidAccessId constraintAnnotation) {}

    public boolean isValid(String accessID, ConstraintValidatorContext context) {
        return (validateAccessID(accessID));
    }

    private boolean validateAccessID(String accessID) {
        Pattern pattern = Pattern.compile(VALID_ACCESS_ID);
        Matcher matcher = pattern.matcher(accessID);

        return matcher.matches();
    }

}
