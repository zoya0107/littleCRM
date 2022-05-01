package com.pet.littlecrm.controllers;

import com.pet.littlecrm.model.Person;
import com.pet.littlecrm.security.PersonDetailsService;
import com.pet.littlecrm.service.MessageService;
import com.pet.littlecrm.service.PersonService;
import com.pet.littlecrm.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/home")
public class HomeController {
    private final MessageService messageService;
    private final TaskService taskService;
    private final PersonService personService;
    private final PersonDetailsService personDetailsService;

    @Autowired
    public HomeController(MessageService messageService, TaskService taskService, PersonService personService, PersonDetailsService personDetailsService) {
        this.messageService = messageService;
        this.taskService = taskService;
        this.personService = personService;
        this.personDetailsService = personDetailsService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('people:read')")
    public String getHomePage(Model model) {
        Person person = personService.getPersonByLogin(personDetailsService.getCurrentPerson());
        model.addAttribute("curperson", person);
        model.addAttribute("listMessages", messageService.getMessages());
        model.addAttribute("listTasks", taskService.getTasks());
        return "home-page";
    }
}
