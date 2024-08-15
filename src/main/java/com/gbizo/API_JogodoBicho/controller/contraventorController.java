package com.gbizo.API_JogodoBicho.controller;

import com.gbizo.API_JogodoBicho.model.contraventor;
import com.gbizo.API_JogodoBicho.service.contraventorService;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contraventor")
public class contraventorController {

    @Autowired
    private contraventorService service;

    @PostMapping("/create")
    public ResponseEntity<contraventor>createContraventor(@RequestBody contraventor contraventor){
        contraventor newContraventor = service.createContraventor(contraventor);
        return new ResponseEntity<>(newContraventor, HttpStatus.CREATED);
    }

    @GetMapping("/selectAll")
    public ResponseEntity<List<contraventor>> getAllContraventor(){
        List<contraventor> allContraventor = service.getAllContraventor();
        return new ResponseEntity<>(allContraventor, HttpStatus.OK);
    }

    @GetMapping("/select/{id_cont}")
    public ResponseEntity<contraventor> getContraventorById(@PathVariable Long id_cont){
        List<contraventor> byId = service.getContraventorById(id_cont);
        return new ResponseEntity<>(byId.get(0), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id_cont}")
    public void deleteContraventor(@PathVariable Long id_cont){
        service.deleteContraventor(id_cont);
    }

    @PutMapping("/edit/{id_cont}")
    public ResponseEntity<contraventor> updateContraventor(@RequestBody contraventor contraventor){
        contraventor updatedContraventor = service.updateContraventor(contraventor);
        return new ResponseEntity<>(updatedContraventor, HttpStatus.OK);
    }
}
