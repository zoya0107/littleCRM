package com.pet.littlecrm.service;

import com.pet.littlecrm.model.Message;
import com.pet.littlecrm.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void saveMessage(Message message) {
        this.messageRepository.save(message);
    }

    public Message getMessageById(Long id) {
        Optional<Message> optional = messageRepository.findById(id);
        Message message = null;
        if (optional.isPresent()) {
            message = optional.get();
        } else {
            throw new RuntimeException("There is no message with id " + id);
        }
        return message;
    }

    public List<Message> getMessagesByReceiver(String login) {
        return messageRepository.findMessagesByReceiver(login).stream()
                .sorted(Comparator.comparing(Message::getId).reversed())
                .collect(Collectors.toList());
    }

    public List<Message> getMessagesByAuthor(String login) {
        return messageRepository.findMessagesByAuthor(login).stream()
                .sorted(Comparator.comparing(Message::getId).reversed())
                .collect(Collectors.toList());
    }
}
