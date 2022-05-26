package com.pet.littlecrm.repository;

import com.pet.littlecrm.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT t FROM Task t WHERE t.receiver = ?1")
    List<Task> findTasksByReceiver(String login);

    @Query("SELECT t FROM Task t WHERE t.author = ?1")
    List<Task> findTasksByAuthor(String login);
}
