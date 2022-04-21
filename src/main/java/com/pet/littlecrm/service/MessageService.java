package com.pet.littlecrm.service;

import com.pet.littlecrm.model.Message;
import com.pet.littlecrm.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> getMessages() {
        return messageRepository.findAll();
    }

    public void saveMessage(Message message) {
        this.messageRepository.save(message);
    }

    public Message getMessageById(Long id) {
        Optional<Message> optional = messageRepository.findById(id);
        Message message = null;
        if (optional.isPresent()) {
            message = optional.get();
        }
        else {
            throw new RuntimeException("There is no order with id " + id);
        }
        return message;
    }
}
