package com.pet.littlecrm.service;


import com.pet.littlecrm.model.Person;
import com.pet.littlecrm.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getPeople() {
        return personRepository.findAll();
    }

}
