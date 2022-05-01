package com.pet.littlecrm.annotation;

import com.pet.littlecrm.security.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueLoginValidator implements ConstraintValidator<UniqueLogin, String> {
    @Autowired
    private PersonDetailsService personDetailsService;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s != null && !personDetailsService.isLoginAlreadyInUse(s);
    }
}
