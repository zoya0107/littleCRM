package com.pet.littlecrm.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

@Constraint(validatedBy = UniqueLoginValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({FIELD, METHOD})
public @interface UniqueLogin {
    String message() default "This login is already taken";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
