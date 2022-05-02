package com.pet.littlecrm.controllers;

import com.pet.littlecrm.model.Completion;
import com.pet.littlecrm.model.Person;
import com.pet.littlecrm.model.Task;
import com.pet.littlecrm.security.PersonDetailsService;
import com.pet.littlecrm.service.PersonService;
import com.pet.littlecrm.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
@RequestMapping(path = "/task")
public class TaskController {
    private final PersonService personService;
    private final TaskService taskService;
    private final PersonDetailsService personDetailsService;

    @Autowired
    public TaskController(PersonService personService, TaskService taskService, PersonDetailsService personDetailsService) {
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
    public String saveNewTask(@ModelAttribute("task") @Valid Task task, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            Person person = personService.getPersonByLogin(personDetailsService.getCurrentPerson());
            model.addAttribute("person", person);
            return "create-task-page";
        }
        task.setAuthor(personDetailsService.getCurrentPerson());
        task.setDate(LocalDate.now());
        task.setCompletion(Completion.START);
        task.setSolution("Enter your solution here");
        taskService.saveTask(task);
        System.out.println("here");
        return "redirect:/home";
    }

    @GetMapping("/show/{id}")
    @PreAuthorize("hasAuthority('people:read')")
    public String showTask(@PathVariable(value = "id") Long id, Model model) {
        Person person = personService.getPersonByLogin(personDetailsService.getCurrentPerson());
        model.addAttribute("curperson", person);
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "task-page";
    }

    @PostMapping("/done")
    @PreAuthorize("hasAuthority('people:read')")
    public String saveUpdatedTask(@ModelAttribute("task") @Valid Task task, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            Person person = personService.getPersonByLogin(personDetailsService.getCurrentPerson());
            model.addAttribute("curperson", person);
            task.setCompletion(Completion.START); //!!
            return "task-page";
        }
        task.setAuthor(task.getAuthor());
        task.setCompletion(Completion.DONE);
        taskService.saveTask(task);
        return "redirect:/home";
    }

    @PostMapping("/approved/{id}")
    @PreAuthorize("hasAuthority('people:read')")
    public String taskStatusApproved(@PathVariable(value = "id") Long id, Model model) {
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        task.setCompletion(Completion.APPROVED);
        taskService.saveTask(task);
        return "redirect:/home";
    }
}
