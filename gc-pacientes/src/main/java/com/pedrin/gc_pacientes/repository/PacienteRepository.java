package com.pedrin.gc_pacientes.repository;

import com.pedrin.gc_pacientes.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PacienteRepository extends JpaRepository<Paciente, UUID> {

    Optional<Paciente> findByCpfOrTelefoneOrEmail(
            String cpf,
            String telefone,
            String email
    );
}
