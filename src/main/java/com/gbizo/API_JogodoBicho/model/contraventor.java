package com.gbizo.API_JogodoBicho.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contraventor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class contraventor {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Long id_cont;
    String nome_cont, tipo_cont;

    @Column(unique = true, nullable = false)
    String login_cont, senha_cont, cpf_cont;
}
