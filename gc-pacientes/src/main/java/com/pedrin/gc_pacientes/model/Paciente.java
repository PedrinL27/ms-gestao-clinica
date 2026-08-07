package com.pedrin.gc_pacientes.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "pacientes")
@Data
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonPropertyDescription("Nome completo do cliente")
    private UUID id;

    @Column(length = 70, nullable = false)
    @JsonPropertyDescription("Nome completo do cliente")
    private String nome;

    @Column(length = 15, nullable = false, unique = true)
    @JsonPropertyDescription("Cpf valido do cliente")
    private String cpf;

    @Column(length = 20, nullable = false, unique = true)
    private String telefone;

    @Column(length = 50, nullable = false, unique = true)
    @JsonPropertyDescription("E-mail válido")
    private String email;

    @Column(name = "data_nascimento", nullable = false)
    @JsonPropertyDescription("Data nascimento do cliente")
    private LocalDate dataNascimento;

    @Column
    private boolean ativo;
}
