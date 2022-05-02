package com.pet.littlecrm.annotation;

import com.pet.littlecrm.security.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistingLoginValidator implements ConstraintValidator<ExistingLogin, String> {
    @Autowired
    private PersonDetailsService personDetailsService;

    @Override
    public boolean isValid(String login, ConstraintValidatorContext constraintValidatorContext) {
        return login != null && personDetailsService.isLoginAlreadyInUse(login);
    }
}
