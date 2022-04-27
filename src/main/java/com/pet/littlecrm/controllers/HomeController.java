package com.pet.littlecrm.controllers;

import com.pet.littlecrm.service.MessageService;
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

    @Autowired
    public HomeController(MessageService messageService, TaskService taskService) {
        this.messageService = messageService;
        this.taskService = taskService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('people:read')")
    public String getHomePage(Model model) {
        model.addAttribute("listMessages", messageService.getMessages());
        model.addAttribute("listTasks", taskService.getTasks());
        return "home";
    }
}
