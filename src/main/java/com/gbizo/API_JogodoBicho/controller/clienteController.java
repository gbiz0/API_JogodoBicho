package com.gbizo.API_JogodoBicho.controller;
import com.gbizo.API_JogodoBicho.model.cliente;
import com.gbizo.API_JogodoBicho.service.clienteService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cliente")
public class clienteController {

    @Autowired
    private clienteService service;

    @PostMapping("/create")
    public ResponseEntity<cliente>createCliente(@RequestBody cliente cliente){
        cliente newCliente = service.createCliente(cliente);
        return new ResponseEntity<>(newCliente, HttpStatus.CREATED);
    }

    @GetMapping("/selectAll")
    public ResponseEntity<List<cliente>> getAllCliente(){
        List<cliente> allCliente = service.getAllCliente();
        return new ResponseEntity<>(allCliente, HttpStatus.OK);
    }

    @GetMapping("/select/{id_cli}")
    public ResponseEntity<?> getClienteById(@PathVariable Long id_cli){
        Optional<cliente> optionalCliente = service.getClienteById(id_cli);

        if(optionalCliente.isPresent()){
            return new ResponseEntity<>(optionalCliente.get(), HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente Not Found");
        }
    }

    @DeleteMapping("/delete/{id_cli}")
    public void deleteCliente(@PathVariable Long id_cli){
        service.deleteCliente(id_cli);
    }

    @PutMapping("/edit/{id_cli}")
    public ResponseEntity<cliente> updateCliente(@PathVariable Long id_cli, @RequestBody cliente cliente){
        try {
            cliente updatedCliente = service.updateCliente(id_cli, cliente);
            return new ResponseEntity<>(updatedCliente, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
