package com.pet.littlecrm.controllers;

import com.pet.littlecrm.model.Order;
import com.pet.littlecrm.model.Person;
import com.pet.littlecrm.service.OrderService;
import com.pet.littlecrm.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/")
public class PersonController {
    private final PersonService personService;
    private final OrderService orderService;

    @Autowired
    public PersonController(PersonService personService, OrderService orderService) {
        this.personService = personService;
        this.orderService = orderService;
    }

    @GetMapping("/")
    public String loginPage(@ModelAttribute("person") Person person) {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("person") Person person) {
        return "redirect:/main";
    }

    @GetMapping("/main")
    public String mainPage(@ModelAttribute("person") Person person) {
        return "main";
    }

}
