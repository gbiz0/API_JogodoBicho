package com.gbizo.API_JogodoBicho.service;

import com.gbizo.API_JogodoBicho.model.contraventor;
import com.gbizo.API_JogodoBicho.repository.contraventorRepository;
import org.hibernate.sql.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class contraventorService {
    @Autowired
    private contraventorRepository repository;

    //Create Contraventor
    public contraventor createContraventor(contraventor contraventor) {
        return repository.save(contraventor);
    }

    //List Contraventor
    public List<contraventor> getAllContraventor() {
        return repository.findAll();
    }

    //List by Contraventor id
    public List<contraventor> getContraventorById(Long id_cont) {
        return repository.findAllById(List.of(id_cont));
    }

    //Delete Contraventor
    public void deleteContraventor(Long id_cont) {
         repository.deleteById(id_cont);
    }

    public contraventor updateContraventor(contraventor contraventor) {
        return repository.save(contraventor);
    }
}
