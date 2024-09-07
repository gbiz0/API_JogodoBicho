package com.gbizo.API_JogodoBicho.repository;

import com.gbizo.API_JogodoBicho.model.contraventor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface contraventorRepository extends JpaRepository<contraventor, Long> {
    UserDetails findByLogin(String login);
}
