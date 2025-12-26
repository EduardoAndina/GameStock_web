package com.edu.gamestock.web.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Entity
@Table(name = "jogo")
public class Jogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "Categoria é obrigatória")
    private String categoria;

    @NotBlank(message = "Plataforma é obrigatória")
    private String plataforma;

    @Positive(message = "Preço deve ser maior que zero")
    private double preco;

    @Min(value = 0, message = "Quantidade inválida")
    private int quantidade;
}


