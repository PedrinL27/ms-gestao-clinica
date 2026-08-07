package com.pedrin.gc_pacientes.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record SalvarPacienteDTO(
        @NotBlank(message = "Campo requerido")
        @Size(min = 2, max = 70)
        String nome,
        @CPF
        @NotBlank(message = "Campo requerido")
        String cpf,
        @NotBlank(message = "Campo requerido")
        @Size(min = 5, max = 20)
        String telefone,
        @NotBlank(message = "Campo requerido")
        @Size(min = 5, max = 50)
        String email,
        @NotNull(message = "Campo requerido")
        @Past(message = "Nao pode ser uma data futura")
        LocalDate dataNascimento
) {
}
