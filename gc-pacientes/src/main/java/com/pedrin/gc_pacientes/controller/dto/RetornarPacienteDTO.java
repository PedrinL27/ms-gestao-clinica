package com.pedrin.gc_pacientes.controller.dto;

import java.time.LocalDate;
import java.util.UUID;

public record RetornarPacienteDTO(
        UUID id,
        String nome,
        String cpf,
        String telefone,
        String email,
        LocalDate dataNascimento
) {
}
