package com.gbizo.API_JogodoBicho.controller;

import com.gbizo.API_JogodoBicho.model.bicho;
import com.gbizo.API_JogodoBicho.service.bichoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bicho")
public class bichoController {
    @Autowired
    private bichoService service;

    @PostMapping("/create")
    public ResponseEntity<bicho> createBicho(@RequestBody bicho bicho){
        bicho newBicho = service.createBicho(bicho);
        return new ResponseEntity<>(newBicho, HttpStatus.CREATED);
    }

    @GetMapping("/selectAll")
    public ResponseEntity<List<bicho>> getAllBicho(){
        List<bicho> allBicho = service.getAllBicho();
        return new ResponseEntity<>(allBicho, HttpStatus.OK);
    }

    @GetMapping("/select/{id_bicho}")
    public ResponseEntity<?> getBichoById(@PathVariable Long id_bicho){
        Optional<bicho> optionalBicho = service.getBichoById(id_bicho);

        if(optionalBicho.isPresent()){
            return new ResponseEntity<>(optionalBicho.get(), HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bicho Not Found");
        }
    }

    @DeleteMapping("/delete/{id_bicho}")
    public void deleteBicho(@PathVariable Long id_bicho){
        service.deleteBicho(id_bicho);
    }

    @PutMapping("/edit/{id_bicho}")
    public ResponseEntity<bicho> updateBicho(@PathVariable Long id_bicho, @RequestBody bicho bicho){
        try {
            bicho updatedBicho = service.updateBicho(id_bicho, bicho);
            return new ResponseEntity<>(updatedBicho, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
