package com.gbizo.API_JogodoBicho.controller;

import com.gbizo.API_JogodoBicho.config.security.TokenService;
import com.gbizo.API_JogodoBicho.dto.authDTO;
import com.gbizo.API_JogodoBicho.model.contraventor;
import com.gbizo.API_JogodoBicho.service.contraventorService;
import java.util.Optional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contraventor")
public class contraventorController {

    @Autowired
    private contraventorService service;

    @Autowired
    private AuthenticationManager authentication;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid authDTO authDTO){
        var userPassword = new UsernamePasswordAuthenticationToken(authDTO.login(), authDTO.password());
        var auth = this.authentication.authenticate(userPassword);
        var token = tokenService.generateToken((contraventor) auth.getPrincipal());

        return ResponseEntity.ok().build();
    }

    @PostMapping("/create")
    public ResponseEntity<contraventor>createContraventor(@RequestBody @Valid contraventor contraventor){
        contraventor newContraventor = service.createContraventor(contraventor);
        return new ResponseEntity<>(newContraventor, HttpStatus.CREATED);
    }

    @GetMapping("/selectAll")
    public ResponseEntity<List<contraventor>> getAllContraventor(){
        List<contraventor> allContraventor = service.getAllContraventor();
        return new ResponseEntity<>(allContraventor, HttpStatus.OK);
    }

    @GetMapping("/select/{id_cont}")
    public ResponseEntity<?> getContraventorById(@PathVariable Long id_cont){
        Optional<contraventor> optionalContraventor = service.getContraventorById(id_cont);

        if(optionalContraventor.isPresent()){
            return new ResponseEntity<>(optionalContraventor.get(), HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contraventor Not Found");
        }
    }

    @DeleteMapping("/delete/{id_cont}")
    public void deleteContraventor(@PathVariable Long id_cont){
        service.deleteContraventor(id_cont);
    }

    @PutMapping("/edit/{id_cont}")
    public ResponseEntity<contraventor> updateContraventor(@PathVariable Long id_cont, @RequestBody contraventor contraventor){
        try {
            contraventor updatedContraventor = service.updateContraventor(id_cont, contraventor);
            return new ResponseEntity<>(updatedContraventor, HttpStatus.OK);
        }
        catch (Exception e){
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
