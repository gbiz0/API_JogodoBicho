package com.gbizo.API_JogodoBicho.service;

import com.gbizo.API_JogodoBicho.model.contraventor;
import com.gbizo.API_JogodoBicho.repository.contraventorRepository;
import org.hibernate.sql.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.List;
import java.util.Optional;

@Service
public class contraventorService {
    @Autowired
    private contraventorRepository repository;

    private BCryptPasswordEncoder crypt = new BCryptPasswordEncoder();

    //Create Contraventor
    public contraventor createContraventor(contraventor contraventor) {
        String cryptPassword = crypt.encode(contraventor.getPassword());
        contraventor.setPassword(cryptPassword);
        return repository.save(contraventor); }

    //List Contraventor
    public List<contraventor> getAllContraventor() {
        return repository.findAll();
    }

    //List by Contraventor id
    public Optional<contraventor> getContraventorById(Long id_cont) {
        return repository.findById(id_cont);
    }

    //Delete Contraventor
    public void deleteContraventor(Long id_cont) {
         repository.deleteById(id_cont);
    }

    //Update Contraventor
    public contraventor updateContraventor(Long id_cont, contraventor contraventor) {
        Optional<contraventor> oldContraventor = repository.findById(id_cont);

        if (oldContraventor.isPresent()) {
            contraventor newContraventor = oldContraventor.get();

            newContraventor.setNome_cont(contraventor.getNome_cont());
            newContraventor.setCpf_cont(contraventor.getCpf_cont());
            newContraventor.setTipo_cont(contraventor.getTipo_cont());
            newContraventor.setLogin(contraventor.getLogin());
            newContraventor.setPassword(contraventor.getPassword());

            return repository.save(newContraventor);
        } else {
            throw new RuntimeException("Contraventor with id: "+id_cont+" not found");
        }
    }
}
