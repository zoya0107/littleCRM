package com.pet.littlecrm.service;

import com.pet.littlecrm.model.Person;
import com.pet.littlecrm.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getPeople() {
        return personRepository.findAll();
    }

    public void savePerson(Person person) {
        this.personRepository.save(person);
    }

    public Person getPersonById(Long id) {
        Optional<Person> optional = personRepository.findById(id);
        Person person = null;
        if (optional.isPresent()) {
            person = optional.get();
        }
        else {
            throw new RuntimeException("There is no person with id " + id);
        }
        return person;
    }

    public Person getPersonByLogin(String login) {
        Optional<Person> optional = personRepository.findPeopleByLogin(login);
        Person person = null;
        if (optional.isPresent()) {
            person = optional.get();
        }
        else {
            throw new RuntimeException("There is no person with login " + login);
        }
        return person;
    }

    public void deletePersonById(Long id) {
        this.personRepository.deleteById(id);
    }

}
