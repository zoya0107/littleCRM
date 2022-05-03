package com.pet.littlecrm.model;

import com.pet.littlecrm.annotation.ExistingLogin;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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
    private Long id;
    @Column(name = "author")
    private String author;
    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @Column(name = "receiver")
    @ExistingLogin
    @NotEmpty(message = "Receiver is required")
    private String receiver;
    @Column(name = "content")
    @NotEmpty(message = "Content is required")
    private String content;
    @Column(name = "topic")
    @NotEmpty(message = "Topic is required")
    private String topic;

    @ManyToOne
    @JoinColumn(name = "receiver", referencedColumnName = "login", insertable = false, updatable = false)
    private PersonLogin receiverPersonLogin;

    @ManyToOne
    @JoinColumn(name = "author", referencedColumnName = "login", insertable = false, updatable = false)
    private PersonLogin authorPersonLogin;

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

    public PersonLogin getReceiverPersonLogin() {
        return receiverPersonLogin;
    }

    public void setReceiverPersonLogin(PersonLogin receiverPersonLogin) {
        this.receiverPersonLogin = receiverPersonLogin;
    }

    public PersonLogin getAuthorPersonLogin() {
        return authorPersonLogin;
    }

    public void setAuthorPersonLogin(PersonLogin authorPersonLogin) {
        this.authorPersonLogin = authorPersonLogin;
    }
}
