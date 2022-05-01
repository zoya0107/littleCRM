package com.pet.littlecrm.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "message")
public class Message implements Serializable {
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
    @Column(name = "author")
    public String author;
    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate date;
    @Column(name = "receiver")
    public String receiver;
    @Column(name = "content")
    public String content;
    @Column(name = "topic")
    public String topic;

    @ManyToOne
    @JoinColumn(name = "receiver", referencedColumnName = "login", insertable = false, updatable = false)
    private Person personReceiver;

    @ManyToOne
    @JoinColumn(name = "author", referencedColumnName = "login", insertable = false, updatable = false)
    private Person personAuthor;

    public Message(Long id, String author, LocalDate date, String receiver, String content, String topic) {
        this.id = id;
        this.author = author;
        this.date = date;
        this.receiver = receiver;
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
        return date;
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

    public Person getPersonReceiver() {
        return personReceiver;
    }

    public void setPersonReceiver(Person personReceiver) {
        this.personReceiver = personReceiver;
    }

    public Person getPersonAuthor() {
        return personAuthor;
    }

    public void setPersonAuthor(Person personAuthor) {
        this.personAuthor = personAuthor;
    }
}
