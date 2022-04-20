package com.pet.littlecrm.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table (name = "person")
@Data
public class Person {

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

    @Column(name="firstname")
    public String firstname;
    @Column(name= "username")
    public String surname;
    @Column(name="login")
    public String login;
    @Column(name="password")
    public String password;
    @Enumerated(value = EnumType.STRING)
    @Column(name="role")
    private Role role;
    @Enumerated(value = EnumType.STRING)
    @Column(name="status")
    private Status status;

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

}
