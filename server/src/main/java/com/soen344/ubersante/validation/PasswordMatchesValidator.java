package com.soen344.ubersante.validation;

import com.soen344.ubersante.users.dto.PatientRegistrationForm;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        PatientRegistrationForm patientRegistrationForm = (PatientRegistrationForm) obj;
        return patientRegistrationForm.getPassword().equals(patientRegistrationForm.getMatchingPassword());
    }
}
