package com.saudeFacil.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "agendamentos")
@Data @NoArgsConstructor @AllArgsConstructor
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "unidade_id", nullable = false)
    private UnidadeSaude unidade;

    @Column(nullable = false)
    private LocalDate dataConsulta;

    @Column(nullable = false)
    private LocalTime horarioConsulta;

    @Column
    private String especialidade;

    // Status possíveis: AGENDADO, CONFIRMADO, CANCELADO, REALIZADO
    @Column(nullable = false)
    private String status = "AGENDADO";

    @Column
    private String observacoes;
}
