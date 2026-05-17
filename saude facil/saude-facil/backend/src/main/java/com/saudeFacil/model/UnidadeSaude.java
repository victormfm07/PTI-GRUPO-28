package com.saudeFacil.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "unidades_saude")
@Data @NoArgsConstructor @AllArgsConstructor
public class UnidadeSaude {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false)
    private String bairro;

    @Column(nullable = false)
    private String cidade;

    @Column
    private String telefone;

    // Tipo: UBS, Hospital, UPA
    @Column(nullable = false)
    private String tipo;

    @Column
    private String horarioFuncionamento;
}
