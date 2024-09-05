package com.gbizo.API_JogodoBicho.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bicho")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class bicho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id_bicho;
    String nome_bicho, tipo_aposta, banca_aposta, forma_pagamento;
    int id_cont, id_cli;
    double val_aposta;
}
