package com.pet.littlecrm.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table (name = "message")
public class Message {
    @Id
    @SequenceGenerator(
            name = "order_sequence",
            sequenceName = "order_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_sequence"
    )
    public Long id;
    @Column(name="author")
    public String author;
    @Column(name="date")
    public LocalDate date;
    @Column(name="receiver")
    public String receiver;
    @Column(name="duedate")
    public LocalDate duedate;
    @Column(name= "content")
    public String content;
    @Column(name="topic")
    public String topic;

    public Message(Long id, String author, LocalDate date, String receiver, LocalDate duedate, String content, String topic) {
        this.id = id;
        this.author = author;
        this.date = date;
        this.receiver = receiver;
        this.duedate = duedate;
        this.content = content;
        this.topic = topic;
    }

    public Message() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getDate() {
        return LocalDate.now();
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public LocalDate getDueDate() {
        return duedate;
    }

    public void setDueDate(LocalDate duedate) {
        this.duedate = duedate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
