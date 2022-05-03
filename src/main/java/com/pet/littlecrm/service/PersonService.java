package com.pet.littlecrm.service;

import com.pet.littlecrm.model.Person;
import com.pet.littlecrm.repository.LoginRepository;
import com.pet.littlecrm.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final LoginRepository loginRepository;

    @Autowired
    public PersonService(PersonRepository personRepository, LoginRepository loginRepository) {
        this.personRepository = personRepository;
        this.loginRepository = loginRepository;
    }

    public List<Person> getPeople() {
        return personRepository.findAll().stream()
                .sorted(Comparator.comparing(Person::getSurname).thenComparing(Person::getFirstname))
                .collect(Collectors.toList());
    }

    public void savePerson(Person person) {
        this.personRepository.save(person);
    }

    public Person getPersonById(Long id) {
        Optional<Person> optional = personRepository.findById(id);
        Person person = null;
        if (optional.isPresent()) {
            person = optional.get();
        } else {
            throw new RuntimeException("There is no person with id " + id);
        }
        return person;
    }

    public Person getPersonByLogin(String login) {
        Long id = loginRepository.findIdByLogin(login).get().getId();
        Optional<Person> optional = personRepository.findPeopleById(id);
        Person person = null;
        if (optional.isPresent()) {
            person = optional.get();
        } else {
            throw new RuntimeException("There is no person with login " + login);
        }
        return person;
    }

    public void deletePersonById(Long id) {
        this.personRepository.deleteById(id);
    }

}
