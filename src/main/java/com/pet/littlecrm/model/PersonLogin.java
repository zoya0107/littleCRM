package com.pet.littlecrm.model;

import com.pet.littlecrm.annotation.UniqueLogin;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Entity
@Table(name = "person_login")
public class PersonLogin implements Serializable {
    @Id
    @SequenceGenerator(
            name = "login_sequence",
            sequenceName = "login_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "login_sequence"
    )
    private Long id;

    @NotEmpty(message = "Login is required")
    @Length(min = 2, max = 50, message = "Login length should be between 2 and 50 characters")
    @Pattern(regexp = "[a-zA-Z0-9]*", message = "Login should contain alpha characters and/or numbers only")
    @UniqueLogin
    @Column(name = "login")
    private String login;

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    private Person person;

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
