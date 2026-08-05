package com.pedrin.gc_pacientes.service;

import com.pedrin.gc_pacientes.model.Paciente;
import com.pedrin.gc_pacientes.repository.PacienteRepository;
import com.pedrin.gc_pacientes.service.exception.PacienteNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PacienteService {

    private final PacienteRepository repository;

    public void salvar(Paciente paciente) {
        paciente.setAtivo(true);
        repository.save(paciente);
    }

    public List<Paciente> findAll() {
        List<Paciente> pacientes = repository.findAll();
        pacientes.removeIf(paciente ->
                !paciente.isAtivo());
       return pacientes;
    }

    public Paciente findById(UUID id) {
        return repository.findById(id)
                .filter(Paciente::isAtivo)
                .orElseThrow(() ->
                        new PacienteNaoEncontradoException("Paciente não encontrado"));
    }

    @Transactional
    public void delete(UUID id) throws PacienteNaoEncontradoException {
        Paciente paciente = findById(id);
        paciente.setAtivo(false);
    }
}
