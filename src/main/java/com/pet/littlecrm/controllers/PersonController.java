package com.pet.littlecrm.controllers;

import com.pet.littlecrm.model.Person;
import com.pet.littlecrm.service.OrderService;
import com.pet.littlecrm.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/v1/people")
public class PersonController {
    private final PersonService personService;
    private final OrderService orderService;

    @Autowired
    public PersonController(PersonService personService, OrderService orderService) {
        this.personService = personService;
        this.orderService = orderService;
    }

    @GetMapping
    public List<Person> getAll() {
        return personService.getPeople();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('people:read')")
    public Person getById(@PathVariable Long id) {
        return personService.getPersonById(id);
    }

}
