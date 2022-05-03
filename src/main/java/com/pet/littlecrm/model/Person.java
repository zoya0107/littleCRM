package com.pet.littlecrm.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Entity
@Table(name = "person")
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
    private Long id;

    @NotEmpty(message = "Name is required")
    @Length(min = 2, max = 50, message = "Name length should be between 2 and 50 characters")
    @Pattern(regexp = "[a-zA-Z]*", message = "Name should contain alpha characters only")
    @Column(name = "firstname")
    private String firstname;

    @NotEmpty(message = "Surname is required")
    @Length(min = 2, max = 50, message = "Surname length should be between 2 and 50 characters")
    @Pattern(regexp = "([a-zA-Z]*)", message = "Surname should contain alpha characters only")
    @Column(name = "surname")
    private String surname;

    @NotEmpty(message = "Password is required")
    @Length(min = 10, max = 50, message = "Password length should be between 10 and 50 characters")
    @Column(name = "password")
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;
    @Column(name = "existence")
    private boolean existence;

    @OneToOne(mappedBy = "person")
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    private PersonLogin personLogin;

    public Person() {
    }

    public Person(Long id, String firstname, String surname, String password) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
        this.password = password;
    }

    public PersonLogin getPersonLogin() {
        return personLogin;
    }

    public void setPersonLogin(PersonLogin personLogin) {
        this.personLogin = personLogin;
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
