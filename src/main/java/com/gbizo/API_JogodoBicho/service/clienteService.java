package com.gbizo.API_JogodoBicho.service;
import com.gbizo.API_JogodoBicho.model.cliente;
import com.gbizo.API_JogodoBicho.repository.clienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class clienteService {
    @Autowired
    private clienteRepository repository;

    //Create Cliente
    public cliente createCliente(cliente cliente) {
        return repository.save(cliente);
    }
    //List Cliente
    public List<cliente> getAllCliente() {
        return repository.findAll();
    }

    //List by Cliente id
    public Optional<cliente> getClienteById(Long id_cli) {
        return repository.findById(id_cli);
    }

    //Delete Cliente
    public void deleteCliente(Long id_cli) {
        repository.deleteById(id_cli);
    }

    //Update Cliente
    public cliente updateCliente(Long id_cli, cliente cliente) {
        Optional<cliente> oldCliente = repository.findById(id_cli);

        if (oldCliente.isPresent()) {
            cliente newCliente = oldCliente.get();

            newCliente.setNome_cli(cliente.getNome_cli());
            newCliente.setLogradouro_cli(cliente.getLogradouro_cli());
            newCliente.setNumero_cli(cliente.getNumero_cli());
            newCliente.setBairro_cli(cliente.getBairro_cli());
            newCliente.setCidade_cli(cliente.getCidade_cli());
            newCliente.setEstado_cli(cliente.getEstado_cli());
            newCliente.setCep_cli(cliente.getCep_cli());
            newCliente.setCpf_cli(cliente.getCpf_cli());
            newCliente.setRg_cli(cliente.getRg_cli());

            return repository.save(newCliente);
        } else {
            throw new RuntimeException("Cliente with id: "+id_cli+" not found");
        }
    }
}
