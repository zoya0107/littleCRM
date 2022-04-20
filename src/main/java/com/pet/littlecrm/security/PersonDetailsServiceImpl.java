package com.pet.littlecrm.security;

import com.pet.littlecrm.model.Person;
import com.pet.littlecrm.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("personDetailsServiceImpl")
public class PersonDetailsServiceImpl implements UserDetailsService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonDetailsServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Person person = personRepository.findPeopleByLogin(login).orElseThrow(() -> new UsernameNotFoundException("User doesn't exist"));
        return SecurityPerson.fromPerson(person);
    }
}
