package com.gbizo.API_JogodoBicho.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "bicho")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class bicho {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Long id_bicho;
    String nome_bicho, tipo_aposta, banca_aposta, forma_pagamento;
    Double val_aposta;
    Date data_aposta;
    Long id_cont, id_cli;
}
