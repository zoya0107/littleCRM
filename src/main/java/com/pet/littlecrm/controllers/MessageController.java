package com.pet.littlecrm.controllers;

import com.pet.littlecrm.model.Message;
import com.pet.littlecrm.model.Person;
import com.pet.littlecrm.security.PersonDetailsServiceImpl;
import com.pet.littlecrm.service.MessageService;
import com.pet.littlecrm.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping(path = "/message")
public class MessageController {
    private final PersonService personService;
    private final MessageService messageService;
    private final PersonDetailsServiceImpl personDetailsService;

    @Autowired
    public MessageController(PersonService personService, MessageService messageService, PersonDetailsServiceImpl personDetailsService) {
        this.personService = personService;
        this.messageService = messageService;
        this.personDetailsService = personDetailsService;
    }

    @GetMapping("/create")
    @PreAuthorize("hasAuthority('people:read')")
    public String createNewMessage(Model model) {
        Person person = personService.getPersonByLogin(personDetailsService.getCurrentPerson());
        model.addAttribute("person", person);
        model.addAttribute("message", new Message());
        return "create-message-page";
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('people:read')")
    public String saveNewMessage(@ModelAttribute("message") Message message) {
        message.setAuthor(personDetailsService.getCurrentPerson());
        message.setDate(LocalDate.now());
        messageService.saveMessage(message);
        return "redirect:/home";
    }

    @GetMapping("/show/{id}")
    @PreAuthorize("hasAuthority('people:read')")
    public String showMessage(@PathVariable(value = "id") Long id, Model model) {
        Message message = messageService.getMessageById(id);
        model.addAttribute("message", message);
        return "message-page";
    }
}
