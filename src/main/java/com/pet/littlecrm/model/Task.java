package com.pet.littlecrm.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "task")
@Data
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
    public Long id;
    @Column(name = "author")
    public String author;
    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate date;
    @Column(name = "duedate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate duedate;
    @Column(name = "receiver")
    public String receiver;
    @Column(name = "content")
    public String content;
    @Column(name = "topic")
    public String topic;
    @Column(name = "urgency")
    public boolean urgent;

    @ManyToOne
    @JoinColumn(name = "receiver", referencedColumnName = "login", insertable = false, updatable = false)
    private Person personReceiver;

    @ManyToOne
    @JoinColumn(name = "author", referencedColumnName = "login", insertable = false, updatable = false)
    private Person personAuthor;

    public Task(Long id, String author, LocalDate date, LocalDate duedate, String receiver, String content, String topic, boolean urgent) {
        this.id = id;
        this.author = author;
        this.date = date;
        this.duedate = duedate;
        this.receiver = receiver;
        this.content = content;
        this.topic = topic;
        this.urgent = urgent;
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
}
