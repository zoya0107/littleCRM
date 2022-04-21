package com.pet.littlecrm.controllers;

import com.pet.littlecrm.model.Message;
import com.pet.littlecrm.model.Person;
import com.pet.littlecrm.service.MessageService;
import com.pet.littlecrm.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/home")
public class PersonController {
    private final PersonService personService;
    private final MessageService messageService;

    @Autowired
    public PersonController(PersonService personService, MessageService messageService) {
        this.personService = personService;
        this.messageService = messageService;
    }

    @GetMapping
    public String getHomePage(Model model) {
        model.addAttribute("listMessages", messageService.getMessages());
        return "home";
    }

    @GetMapping("/createmessageform")
    public String getNewMessagePage(Model model) {
        model.addAttribute("message", new Message());
        return "newmessage";
    }

    @PostMapping("/createmessage")
    public String createNewMessage(@ModelAttribute("message") Message message) {
        messageService.saveMessage(message);
        return "redirect:/home";
    }

    @GetMapping("/showmessage/{id}")
    public String showMessage(@PathVariable (value="id") Long id, Model model) {
        Message message = messageService.getMessageById(id);
        model.addAttribute("message", message);
        return "read";
    }

    @GetMapping("/createpersonform")
    public String getNewPersonPage(Model model) {
        model.addAttribute("person", new Person());
        return "add";
    }

    @PostMapping("/add")
    public String addPerson(@ModelAttribute("person") Person person) {
        personService.savePerson(person);
        return "redirect:/home";
    }

//    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('people:read')")
//    public Person getById(@PathVariable Long id) {
//        return personService.getPersonById(id);
//    }

}
