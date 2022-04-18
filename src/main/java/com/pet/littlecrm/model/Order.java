package com.pet.littlecrm.model;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table
public class Order {
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
    public String author;
    public LocalDate date;
    public String receiver;
    public LocalDate dueDate;
    public String message;

    public Order(Long id, String author, LocalDate date, String receiver, LocalDate dueDate, String message) {
        this.id = id;
        this.author = author;
        this.date = date;
        this.receiver = receiver;
        this.dueDate = dueDate;
        this.message = message;
    }

    public Order() {
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

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
