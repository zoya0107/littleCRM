package com.pet.littlecrm.controllers;

import com.pet.littlecrm.model.*;
import com.pet.littlecrm.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(path = "/home")
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/add/person")
    @PreAuthorize("hasAuthority('people:write')")
    public String addNewPerson(Model model) {
        model.addAttribute("person", new Person());
        List<Role> roleOptions = new ArrayList<Role>(Arrays.asList(Role.values()));
        model.addAttribute("roleoptions", roleOptions);
        List<Status> statusOptions = new ArrayList<Status>(Arrays.asList(Status.values()));
        model.addAttribute("statusoptions", statusOptions);
        return "add";
    }

    @PostMapping("/{login}/save/person")
    @PreAuthorize("hasAuthority('people:write')")
    public String saveNewPerson(@ModelAttribute("person") Person person, Model model) {
        personService.savePerson(person);
        return "redirect:/home/{login}/show/people";
    }

    @GetMapping("{login}/show/people")
    @PreAuthorize("hasAuthority('people:read')")
    public String getListPeople(@PathVariable(value = "login") String login, Model model) {
        Person person = personService.getPersonByLogin(login);
        model.addAttribute("curperson", person);
        model.addAttribute("listPeople", personService.getPeople());
        return "listpeople";
    }

    @GetMapping("/update/person/{id}")
    @PreAuthorize("hasAuthority('people:write')")
    public String updatePerson(@PathVariable(value = "id") Long id, Model model) {
        Person person = personService.getPersonById(id);
        model.addAttribute("person", person);
        List<Role> roleOptions = new ArrayList<Role>(Arrays.asList(Role.values()));
        model.addAttribute("roleoptions", roleOptions);
        List<Status> statusOptions = new ArrayList<Status>(Arrays.asList(Status.values()));
        model.addAttribute("statusoptions", statusOptions);
        return "update";
    }

    @GetMapping("/{login}/delete/person/{id}")
    @PreAuthorize("hasAuthority('people:write')")
    public String deletePerson(@PathVariable(value = "id") Long id, Model model) {
        personService.deletePersonById(id);
        return "redirect:/home/{login}/show/people";
    }
}
