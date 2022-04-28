package com.pet.littlecrm.controllers;

import com.pet.littlecrm.model.Person;
import com.pet.littlecrm.model.Task;
import com.pet.littlecrm.security.PersonDetailsServiceImpl;
import com.pet.littlecrm.service.PersonService;
import com.pet.littlecrm.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping(path = "/task")
public class TaskController {
    private final PersonService personService;
    private final TaskService taskService;
    private final PersonDetailsServiceImpl personDetailsService;

    @Autowired
    public TaskController(PersonService personService, TaskService taskService, PersonDetailsServiceImpl personDetailsService) {
        this.personService = personService;
        this.taskService = taskService;
        this.personDetailsService = personDetailsService;
    }

    @GetMapping("/create")
    @PreAuthorize("hasAuthority('people:read')")
    public String createNewTask(Model model) {
        Person person = personService.getPersonByLogin(personDetailsService.getCurrentPerson());
        model.addAttribute("person", person);
        model.addAttribute("task", new Task());
        return "create-task-page";
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('people:read')")
    public String saveNewTask(@ModelAttribute("task") Task task) {
        task.setAuthor(personDetailsService.getCurrentPerson());
        task.setDate(LocalDate.now());
        taskService.saveTask(task);
        return "redirect:/home";
    }

    @GetMapping("/show/{id}")
    @PreAuthorize("hasAuthority('people:read')")
    public String showTask(@PathVariable(value = "id") Long id, Model model) {
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "task-page";
    }
}
