package com.pet.littlecrm.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = FutureDateValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface FutureDate {
    String message() default "Date should not be past";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
