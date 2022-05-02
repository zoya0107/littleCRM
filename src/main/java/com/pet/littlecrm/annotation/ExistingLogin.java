package com.pet.littlecrm.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ExistingLoginValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface ExistingLogin {
    String message() default "There is no such login";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
