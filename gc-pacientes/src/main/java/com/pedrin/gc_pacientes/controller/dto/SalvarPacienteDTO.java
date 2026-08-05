package com.pedrin.gc_pacientes.controller.dto;

import java.time.LocalDate;

public record SalvarPacienteDTO(
        String nome,
        String cpf,
        String telefone,
        String email,
        LocalDate dataNascimento
) {
}
