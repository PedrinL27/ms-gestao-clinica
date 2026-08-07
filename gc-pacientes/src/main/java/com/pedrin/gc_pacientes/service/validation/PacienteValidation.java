package com.pedrin.gc_pacientes.service.validation;

import com.pedrin.gc_pacientes.model.Paciente;
import com.pedrin.gc_pacientes.repository.PacienteRepository;
import com.pedrin.gc_pacientes.service.exception.RegistroDuplicadoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PacienteValidation {

    private final PacienteRepository repository;

    public void validarPaciente(Paciente paciente) {
        repository.findByCpfOrTelefoneOrEmail(
                paciente.getCpf(),
                paciente.getTelefone(),
                paciente.getEmail()).ifPresent(p -> {
            if (p.getCpf().equals(paciente.getCpf())) {
                throw new RegistroDuplicadoException("CPF já cadastrado.", "cpf");
            }
            if (p.getTelefone().equals(paciente.getTelefone())) {
                throw new RegistroDuplicadoException("Telefone já cadastrado.", "telefone");
            }

            if (p.getEmail().equalsIgnoreCase(paciente.getEmail())) {
                throw new RegistroDuplicadoException("E-mail já cadastrado.", "email");
            }
        });
    }


}
