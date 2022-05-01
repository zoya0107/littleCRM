package com.pet.littlecrm.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "person")
@Data
public class Person implements Serializable {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    public Long id;

    @NotEmpty(message = "Name is required")
    @Length(min = 2, max = 50, message = "Name length should be between 2 and 50 characters")
    @Pattern(regexp = "[a-zA-Z]*", message = "Name should contain alpha characters only")
    @Column(name = "firstname")
    public String firstname;

    @NotEmpty(message = "Surname is required")
    @Length(min = 2, max = 50, message = "Surname length should be between 2 and 50 characters")
    @Pattern(regexp = "([a-zA-Z]*)", message = "Surname should contain alpha characters only")
    @Column(name = "surname")
    public String surname;

    @NotEmpty(message = "Login is required")
    @Length(min = 2, max = 50, message = "Login length should be between 2 and 50 characters")
    @Pattern(regexp = "[a-zA-Z0-9]*", message = "Login should contain alpha characters and/or numbers only")
    @Column(name = "login")
    public String login;

    @NotEmpty(message = "Password should not be empty")
    @Length(min = 10, max = 50, message = "Password length should be between 10 and 50 characters")
    @Column(name = "password")
    public String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;
    @Column(name = "existence")
    private boolean existence;

    @OneToMany(mappedBy = "personReceiver")
    private List<Message> listMessages;

    public Person() {
    }

    public Person(Long id, String firstname, String surname, String login, String password) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
        this.login = login;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isExistence() {
        return existence;
    }

    public void setExistence(boolean existence) {
        this.existence = existence;
    }
}
