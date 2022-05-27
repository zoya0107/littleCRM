package com.pet.littlecrm.service;

import com.pet.littlecrm.model.Message;
import com.pet.littlecrm.repository.MessageRepository;
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
public class MessageServiceTest {
    @Autowired
    private MessageService messageService;
    @MockBean
    private MessageRepository messageRepository;

    @Test
    @DisplayName("Test getMessageById")
    void testGetMessageById() {
        Message message = new Message(1L, "author", LocalDate.now(), "receiver", "content", "topic");
        when(messageRepository.findById(1L)).thenReturn(Optional.of(message));
        Message returnedMessage = messageService.getMessageById(1L);
        Assertions.assertSame(returnedMessage, message, "The message returned was not the same as the mock");
    }

    @Test
    @DisplayName("Test saveMessage")
    public void saveMessage() {
        Message message = new Message(1L, "author", LocalDate.now(), "receiver", "content", "topic");
        messageService.saveMessage(message);
        verify(messageRepository, times(1)).save(message);
    }

    @Test
    @DisplayName("Test findAllByReceiver")
    void testFindAllByReceiver() {
        Message message1 = new Message(1L, "author1", LocalDate.now(), "receiver", "content", "topic");
        Message message2 = new Message(2L, "author2", LocalDate.now(), "receiver", "content", "topic");
        when(messageRepository.findMessagesByReceiver("receiver")).thenReturn(Arrays.asList(message1, message2));
        List<Message> messages = messageService.getMessagesByReceiver("receiver");
        Assertions.assertEquals(2, messages.size(), "findAllByReceiver should return 2 messages");
    }

    @Test
    @DisplayName("Test findAllByAuthor")
    void testFindAllByAuthor() {
        Message message1 = new Message(1L, "author", LocalDate.now(), "receiver1", "content", "topic");
        Message message2 = new Message(2L, "author", LocalDate.now(), "receiver2", "content", "topic");
        when(messageRepository.findMessagesByAuthor("author")).thenReturn(Arrays.asList(message1, message2));
        List<Message> messages = messageService.getMessagesByAuthor("author");
        Assertions.assertEquals(2, messages.size(), "findAllByAuthor should return 2 messages");
    }
}
