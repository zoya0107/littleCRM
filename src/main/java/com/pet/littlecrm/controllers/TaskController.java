package com.pet.littlecrm.controllers;

import com.pet.littlecrm.model.Person;
import com.pet.littlecrm.model.Task;
import com.pet.littlecrm.service.PersonService;
import com.pet.littlecrm.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping(path = "/home")
public class TaskController {
    private final PersonService personService;
    private final TaskService taskService;

    @Autowired
    public TaskController(PersonService personService, TaskService taskService) {
        this.personService = personService;
        this.taskService = taskService;
    }

    @GetMapping("/{login}/create/task")
    @PreAuthorize("hasAuthority('people:read')")
    public String createNewTask(@PathVariable(value = "login") String login, Model model) {
        Person person = personService.getPersonByLogin(login);
        model.addAttribute("person", person);
        model.addAttribute("task", new Task());
        return "newtask";
    }

    @PostMapping("{login}/save/task")
    @PreAuthorize("hasAuthority('people:read')")
    public String saveNewTask(@PathVariable(value = "login") String login, @ModelAttribute("task") Task task) {
        task.setAuthor(login);
        task.setDate(LocalDate.now());
        taskService.saveTask(task);
        return "redirect:/home";
    }

    @GetMapping("/show/task/{id}")
    @PreAuthorize("hasAuthority('people:read')")
    public String showTask(@PathVariable(value = "id") Long id, Model model) {
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "readt";
    }
}
