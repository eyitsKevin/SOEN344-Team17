package com.soen344.ubersante.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, FIELD, ANNOTATION_TYPE, PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = CartValidator.class)
@Documented
public @interface ValidCart {

    String message() default "There is a conflict with the annual conflicts";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
