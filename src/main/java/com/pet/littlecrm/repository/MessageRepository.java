package com.pet.littlecrm.repository;

import com.pet.littlecrm.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("SELECT m FROM Message m WHERE m.receiver = ?1")
    List<Message> findMessagesByReceiver(String login);

    @Query("SELECT m FROM Message m WHERE m.author = ?1")
    List<Message> findMessagesByAuthor(String login);
}