package com.pet.littlecrm.service;

import com.pet.littlecrm.model.PersonLogin;
import com.pet.littlecrm.repository.LoginRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonLoginService {
    private final LoginRepository loginRepository;

    public PersonLoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public void savePersonLogin(PersonLogin personLogin) {
        this.loginRepository.save(personLogin);
    }
}
