package com.pet.littlecrm.security;

import com.pet.littlecrm.model.Person;
import com.pet.littlecrm.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("personDetailsServiceImpl")
public class PersonDetailsService implements UserDetailsService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonDetailsService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Person person = personRepository.findPeopleByLogin(login).orElseThrow(() -> new UsernameNotFoundException("User doesn't exist"));
        return PersonSecurity.fromPerson(person);
    }

    public String getCurrentPerson() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }

    public boolean isLoginAlreadyInUse(String login) {
        Optional<Person> person = personRepository.findPeopleByLogin(login);
        return person.isPresent();
    }
}
