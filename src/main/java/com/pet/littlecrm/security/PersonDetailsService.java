package com.pet.littlecrm.security;

import com.pet.littlecrm.model.Person;
import com.pet.littlecrm.model.PersonLogin;
import com.pet.littlecrm.repository.LoginRepository;
import com.pet.littlecrm.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("personDetailsService")
public class PersonDetailsService implements UserDetailsService {

    private final PersonRepository personRepository;
    private final LoginRepository loginRepository;

    @Autowired
    public PersonDetailsService(PersonRepository personRepository, LoginRepository loginRepository) {
        this.personRepository = personRepository;
        this.loginRepository = loginRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Long id = loginRepository.findIdByLogin(login).get().getId();
        Person person = personRepository.findPeopleById(id).orElseThrow(() -> new UsernameNotFoundException("User doesn't exist"));
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

    public Boolean isLoginAlreadyInUse(String login) {
        Optional<PersonLogin> personLogin = loginRepository.findIdByLogin(login);
        return personLogin.isPresent();
    }
}
