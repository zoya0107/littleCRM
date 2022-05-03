package com.pet.littlecrm.controllers;

import com.pet.littlecrm.model.Person;
import com.pet.littlecrm.model.PersonLogin;
import com.pet.littlecrm.model.Role;
import com.pet.littlecrm.model.Status;
import com.pet.littlecrm.security.PersonDetailsService;
import com.pet.littlecrm.service.PersonLoginService;
import com.pet.littlecrm.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(path = "/person")
public class PersonController {
    private final PersonService personService;
    private final PersonDetailsService personDetailsService;
    private final PersonLoginService personLoginService;

    @Autowired
    public PersonController(PersonService personService, PersonDetailsService personDetailsService, PersonLoginService personLoginService) {
        this.personService = personService;
        this.personDetailsService = personDetailsService;
        this.personLoginService = personLoginService;
    }

    @GetMapping("/add")
    @PreAuthorize("hasAuthority('people:write')")
    public String addNewPerson(Model model) {
        model.addAttribute("person", new Person());
        model.addAttribute("personLogin", new PersonLogin());
        List<Role> roleOptions = new ArrayList<Role>(Arrays.asList(Role.values()));
        model.addAttribute("roleoptions", roleOptions);
        List<Status> statusOptions = new ArrayList<Status>(Arrays.asList(Status.values()));
        model.addAttribute("statusoptions", statusOptions);
        return "add-person-page";
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('people:write')")
    public String saveNewPerson(@ModelAttribute("person") @Valid Person person,
                                @ModelAttribute("personLogin") @Valid PersonLogin personLogin,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<Role> roleOptions = new ArrayList<Role>(Arrays.asList(Role.values()));
            model.addAttribute("roleoptions", roleOptions);
            List<Status> statusOptions = new ArrayList<Status>(Arrays.asList(Status.values()));
            model.addAttribute("statusoptions", statusOptions);
            return "add-person-page";
        }
        person.setExistence(true);
        personService.savePerson(person);
        personLoginService.savePersonLogin(personLogin);
        return "redirect:/person/showlist";
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('people:write')")
    public String savePerson(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<Role> roleOptions = new ArrayList<Role>(Arrays.asList(Role.values()));
            model.addAttribute("roleoptions", roleOptions);
            List<Status> statusOptions = new ArrayList<Status>(Arrays.asList(Status.values()));
            model.addAttribute("statusoptions", statusOptions);
            return "update-person-page";
        }
        person.setExistence(true);
        personService.savePerson(person);
        return "redirect:/person/showlist";
    }

    @GetMapping("/showlist")
    @PreAuthorize("hasAuthority('people:read')")
    public String getListPeople(Model model) {
        Person person = personService.getPersonByLogin(personDetailsService.getCurrentPerson());
        model.addAttribute("curperson", person);
        model.addAttribute("listPeople", personService.getPeople());
        return "list-people-page";
    }

    @GetMapping("/update/{id}")
    @PreAuthorize("hasAuthority('people:write')")
    public String updatePerson(@PathVariable(value = "id") Long id, Model model) {
        Person person = personService.getPersonById(id);
        model.addAttribute("person", person);
        List<Role> roleOptions = new ArrayList<Role>(Arrays.asList(Role.values()));
        model.addAttribute("roleoptions", roleOptions);
        List<Status> statusOptions = new ArrayList<Status>(Arrays.asList(Status.values()));
        model.addAttribute("statusoptions", statusOptions);
        return "update-person-page";
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('people:write')")
    public String deletePerson(@PathVariable(value = "id") Long id, Model model) {
        Person person = personService.getPersonById(id);
        person.setStatus(Status.BANNED);
        person.setExistence(false);
        personService.savePerson(person);
        return "redirect:/person/showlist";
    }
}
