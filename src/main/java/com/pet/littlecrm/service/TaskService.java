package com.pet.littlecrm.service;

import com.pet.littlecrm.model.Task;
import com.pet.littlecrm.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void saveTask(Task task) {
        this.taskRepository.save(task);
    }

    public Task getTaskById(Long id) {
        Optional<Task> optional = taskRepository.findById(id);
        Task task = null;
        if (optional.isPresent()) {
            task = optional.get();
        } else {
            throw new RuntimeException("There is no task with id " + id);
        }
        return task;
    }

    public List<Task> getTasksByReceiver(String login) {
        return taskRepository.findTasksByReceiver(login).stream()
                .sorted(Comparator.comparing(Task::getId).reversed())
                .collect(Collectors.toList());
    }

    public List<Task> getTasksByAuthor(String login) {
        return taskRepository.findTasksByAuthor(login).stream()
                .sorted(Comparator.comparing(Task::getId).reversed())
                .collect(Collectors.toList());
    }
}