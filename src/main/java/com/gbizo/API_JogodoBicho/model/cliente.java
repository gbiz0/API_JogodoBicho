package com.gbizo.API_JogodoBicho.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cliente")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class cliente {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Long id_cli;
    String nome_cli, logradouro_cli, bairro_cli, cidade_cli, estado_cli, cep_cli;
    int numero_cli;
    @Column(unique = true, nullable = false)
    String cpf_cli, rg_cli;
}
