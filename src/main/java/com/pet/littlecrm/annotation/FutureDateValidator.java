package com.pet.littlecrm.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;

public class FutureDateValidator implements ConstraintValidator<FutureDate, LocalDate> {

    @Override
    public boolean isValid(LocalDate duedate, ConstraintValidatorContext constraintValidatorContext) {
        return Period.between(duedate, LocalDate.now()).isNegative();
    }
}
