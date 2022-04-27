package com.pet.littlecrm.controllers;

import com.pet.littlecrm.model.*;
import com.pet.littlecrm.service.MessageService;
import com.pet.littlecrm.service.PersonService;
import com.pet.littlecrm.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(path = "/home")
public class PersonController {
    private final PersonService personService;
    private final MessageService messageService;
    private final TaskService taskService;

    @Autowired
    public PersonController(PersonService personService, MessageService messageService, TaskService taskService) {
        this.personService = personService;
        this.messageService = messageService;
        this.taskService = taskService;
    }

    //main

    @GetMapping
    @PreAuthorize("hasAuthority('people:read')")
    public String getHomePage(Model model) {
        model.addAttribute("listMessages", messageService.getMessages());
        model.addAttribute("listTasks", taskService.getTasks());
        return "home";
    }

    //for messages

    @GetMapping("/{login}/createmessageform/")
    @PreAuthorize("hasAuthority('people:read')")
    public String getNewMessagePage(@PathVariable(value = "login") String login, Model model) {
        Person person = personService.getPersonByLogin(login);
        model.addAttribute("person", person);
        model.addAttribute("message", new Message());
        return "newmessage";
    }

    @PostMapping("/createmessage/{login}")
    @PreAuthorize("hasAuthority('people:read')")
    public String createNewMessage(@PathVariable(value = "login") String login, @ModelAttribute("message") Message message) {
        message.setAuthor(login);
        message.setDate(LocalDate.now());
        messageService.saveMessage(message);
        return "redirect:/home";
    }

    @GetMapping("/showmessage/{id}")
    @PreAuthorize("hasAuthority('people:read')")
    public String showMessage(@PathVariable(value = "id") Long id, Model model) {
        Message message = messageService.getMessageById(id);
        model.addAttribute("message", message);
        return "read";
    }

    //for people

    @GetMapping("/createpersonform")
    @PreAuthorize("hasAuthority('people:write')")
    public String getNewPersonPage(Model model) {
        model.addAttribute("person", new Person());
        List<Role> roleOptions = new ArrayList<Role>(Arrays.asList(Role.values()));
        model.addAttribute("roleoptions", roleOptions);
        List<Status> statusOptions = new ArrayList<Status>(Arrays.asList(Status.values()));
        model.addAttribute("statusoptions", statusOptions);
        return "add";
    }

    @PostMapping("/{login}/add")
    @PreAuthorize("hasAuthority('people:write')")
    public String addPerson(@ModelAttribute("person") Person person, Model model) {
        personService.savePerson(person);
        return "redirect:/home/showlist/{login}";
    }

    @GetMapping("/showlist/{login}")
    @PreAuthorize("hasAuthority('people:read')")
    public String getListPeople(@PathVariable(value = "login") String login, Model model) {
        Person person = personService.getPersonByLogin(login);
        model.addAttribute("curperson", person);
        model.addAttribute("listPeople", personService.getPeople());
        return "listpeople";
    }

    @GetMapping("/update/{id}")
    @PreAuthorize("hasAuthority('people:write')")
    public String showUpdateForm(@PathVariable(value = "id") Long id, Model model) {
        Person person = personService.getPersonById(id);
        model.addAttribute("person", person);
        List<Role> roleOptions = new ArrayList<Role>(Arrays.asList(Role.values()));
        model.addAttribute("roleoptions", roleOptions);
        List<Status> statusOptions = new ArrayList<Status>(Arrays.asList(Status.values()));
        model.addAttribute("statusoptions", statusOptions);
        return "update";
    }

    @GetMapping("/{login}/delete/{id}")
    @PreAuthorize("hasAuthority('people:write')")
    public String deletePerson(@PathVariable(value = "id") Long id, Model model) {
        personService.deletePersonById(id);
        return "redirect:/home/showlist/{login}";
    }

    //for tasks

    @GetMapping("/{login}/createtaskform/")
    @PreAuthorize("hasAuthority('people:read')")
    public String getNewTaskPage(@PathVariable(value = "login") String login, Model model) {
        Person person = personService.getPersonByLogin(login);
        model.addAttribute("person", person);
        model.addAttribute("task", new Task());
        return "newtask";
    }

    @PostMapping("/createtask/{login}")
    @PreAuthorize("hasAuthority('people:read')")
    public String createNewTask(@PathVariable(value = "login") String login, @ModelAttribute("task") Task task) {
        task.setAuthor(login);
        task.setDate(LocalDate.now());
        taskService.saveTask(task);
        return "redirect:/home";
    }

    @GetMapping("/showtask/{id}")
    @PreAuthorize("hasAuthority('people:read')")
    public String showTask(@PathVariable(value = "id") Long id, Model model) {
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "readt";
    }
}
