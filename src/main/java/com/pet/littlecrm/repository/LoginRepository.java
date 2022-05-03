package com.pet.littlecrm.repository;

import com.pet.littlecrm.model.PersonLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<PersonLogin, Long> {
    @Query("SELECT l FROM PersonLogin l WHERE l.login = ?1")
    Optional<PersonLogin> findIdByLogin(String login);
}
