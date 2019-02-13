package com.soen344.ubersante.validation;

import com.soen344.ubersante.users.models.Patient;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        Patient patient = (Patient) obj;
        return patient.getPassword().equals(patient.getMatchingPassword());
    }
}
