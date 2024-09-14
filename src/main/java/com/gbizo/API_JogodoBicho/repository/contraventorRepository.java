package com.gbizo.API_JogodoBicho.repository;

import com.gbizo.API_JogodoBicho.model.contraventor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface contraventorRepository extends JpaRepository<contraventor, Long> {
    contraventor findByLogin(String login);
}
