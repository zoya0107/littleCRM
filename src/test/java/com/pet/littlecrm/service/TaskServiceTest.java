package com.pet.littlecrm.service;

import com.pet.littlecrm.model.Completion;
import com.pet.littlecrm.model.Task;
import com.pet.littlecrm.repository.TaskRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
public class TaskServiceTest {

    @Autowired
    private TaskService taskService;
    @MockBean
    private TaskRepository taskRepository;

    @Test
    @DisplayName("Test getTaskById")
    void testGetTaskById() {
        Task task = new Task(1L, "author", LocalDate.now(), LocalDate.now(), "receiver", "content", "topic", true, "solution", Completion.DONE);
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        Task returnedTask = taskService.getTaskById(1L);
        Assertions.assertSame(returnedTask, task, "The task returned was not the same as the mock");
    }

    @Test
    @DisplayName("Test saveTask")
    public void saveTask() {
        Task task = new Task(1L, "author", LocalDate.now(), LocalDate.now(), "receiver", "content", "topic", true, "solution", Completion.DONE);
        taskService.saveTask(task);
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    @DisplayName("Test findAllByReceiver")
    void testFindAllByReceiver() {
        Task task1 = new Task(1L, "author1", LocalDate.now(), LocalDate.now(), "receiver", "content", "topic", true, "solution", Completion.DONE);
        Task task2 = new Task(2L, "author2", LocalDate.now(), LocalDate.now(), "receiver", "content", "topic", true, "solution", Completion.DONE);
        when(taskRepository.findTasksByReceiver("receiver")).thenReturn(Arrays.asList(task1, task2));
        List<Task> tasks = taskService.getTasksByReceiver("receiver");
        Assertions.assertEquals(2, tasks.size(), "findAllByReceiver should return 2 tasks");
    }

    @Test
    @DisplayName("Test findAllByAuthor")
    void testFindAllByAuthor() {
        Task task1 = new Task(1L, "author", LocalDate.now(), LocalDate.now(), "receiver1", "content", "topic", true, "solution", Completion.DONE);
        Task task2 = new Task(2L, "author", LocalDate.now(), LocalDate.now(), "receiver2", "content", "topic", true, "solution", Completion.DONE);
        when(taskRepository.findTasksByAuthor("author")).thenReturn(Arrays.asList(task1, task2));
        List<Task> tasks = taskService.getTasksByAuthor("author");
        Assertions.assertEquals(2, tasks.size(), "findAllByAuthor should return 2 tasks");
    }
}
