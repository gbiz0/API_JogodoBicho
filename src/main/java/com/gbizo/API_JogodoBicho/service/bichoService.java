package com.gbizo.API_JogodoBicho.service;
import com.gbizo.API_JogodoBicho.model.bicho;
import com.gbizo.API_JogodoBicho.repository.bichoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class bichoService {
    @Autowired
    private bichoRepository repository;

    //Create Bicho
    public bicho createBicho(bicho bicho) {
        return repository.save(bicho);
    }

    //List Bicho
    public List<bicho> getAllBicho() {
        return repository.findAll();
    }

    //List by Bicho id
    public Optional<bicho> getBichoById(Long id_bicho) {
        return repository.findById(id_bicho);
    }

    //Delete Bicho
    public void deleteBicho(Long id_bicho) {
        repository.deleteById(id_bicho);
    }

    //Update Bicho
    public bicho updateBicho(Long id_bicho, bicho bicho) {
        Optional<bicho> oldBicho = repository.findById(id_bicho);

        if (oldBicho.isPresent()) {
            bicho newBicho = oldBicho.get();

            newBicho.setNome_bicho(bicho.getNome_bicho());
            newBicho.setTipo_aposta(newBicho.getTipo_aposta());
            newBicho.setVal_aposta(newBicho.getVal_aposta());
            newBicho.setBanca_aposta(newBicho.getBanca_aposta());
            newBicho.setData_aposta(newBicho.getData_aposta());
            newBicho.setForma_pagamento(newBicho.getForma_pagamento());
            newBicho.setId_cont(newBicho.getId_cont());
            newBicho.setId_cli(newBicho.getId_cli());
            return repository.save(newBicho);
        } else {
            throw new RuntimeException("Bicho with id: "+id_bicho+" not found");
        }
    }
}
