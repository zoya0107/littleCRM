package com.pet.littlecrm.model;

import com.pet.littlecrm.annotation.ExistingLogin;
import com.pet.littlecrm.annotation.FutureDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "task")
public class Task implements Serializable {
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

    @Column(name = "duedate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureDate
    private LocalDate duedate;

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
    @Column(name = "urgency")
    private boolean urgent;
    @Column(name = "solution")
    private String solution;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "completion")
    private Completion completion;

    @ManyToOne
    @JoinColumn(name = "receiver", referencedColumnName = "login", insertable = false, updatable = false)
    private PersonLogin receiverPersonLogin;

    @ManyToOne
    @JoinColumn(name = "author", referencedColumnName = "login", insertable = false, updatable = false)
    private PersonLogin authorPersonLogin;

    public Task(Long id, String author, LocalDate date, LocalDate duedate, String receiver, String content, String topic, boolean urgent, String solution, Completion completion) {
        this.id = id;
        this.author = author;
        this.date = date;
        this.duedate = duedate;
        this.receiver = receiver;
        this.content = content;
        this.topic = topic;
        this.urgent = urgent;
        this.solution = solution;
        this.completion = completion;
    }

    public Task() {
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

    public LocalDate getDuedate() {
        return duedate;
    }

    public void setDuedate(LocalDate duedate) {
        this.duedate = duedate;
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

    public boolean isUrgent() {
        return urgent;
    }

    public void setUrgent(boolean urgent) {
        this.urgent = urgent;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public Completion getCompletion() {
        return completion;
    }

    public void setCompletion(Completion completion) {
        this.completion = completion;
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
