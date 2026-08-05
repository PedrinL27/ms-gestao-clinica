package com.pedrin.gc_pacientes.controller;

import com.pedrin.gc_pacientes.controller.dto.StatusResponse;
import com.pedrin.gc_pacientes.model.Paciente;
import com.pedrin.gc_pacientes.service.PacienteService;
import com.pedrin.gc_pacientes.service.exception.PacienteNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pacientes")
@RequiredArgsConstructor
@Slf4j
public class PacienteController {

    private final PacienteService service;

    @PostMapping
    public ResponseEntity<Void> salvarPaciente(@RequestBody Paciente paciente) {
        service.salvar(paciente);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listarPacientes() {
        List<Paciente> pacientes = service.findAll();
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> acharPorId(@PathVariable("id") String id) {
        UUID uuid = UUID.fromString(id);
        try {
            Paciente paciente = service.findById(uuid);
            return ResponseEntity.ok(paciente);
        } catch (PacienteNaoEncontradoException e) {
            log.error(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPaciente(@PathVariable("id") String id) {
        service.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/status")
    public ResponseEntity<StatusResponse> status() {
        return ResponseEntity.ok(new StatusResponse(
                "Status OK - Paciente",
                LocalDateTime.now()
        ));
    }
}
