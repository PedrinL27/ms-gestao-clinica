package com.pedrin.gc_pacientes.controller;

import com.pedrin.gc_pacientes.controller.dto.RetornarPacienteDTO;
import com.pedrin.gc_pacientes.controller.dto.SalvarPacienteDTO;
import com.pedrin.gc_pacientes.controller.dto.StatusResponse;
import com.pedrin.gc_pacientes.controller.mapper.PacienteMapper;
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

    private final PacienteMapper mapper;

    @PostMapping
    public ResponseEntity<Void> salvarPaciente(@RequestBody SalvarPacienteDTO dto) {
        var paciente = mapper.salvarPacienteDTOtoPaciente(dto);
        service.salvar(paciente);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<RetornarPacienteDTO>> listarPacientes() {
        List<Paciente> pacientes = service.findAll();
        var dtos = pacientes.stream()
                .map(mapper::pacienteToRetornarPacienteDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RetornarPacienteDTO> acharPorId(@PathVariable("id") String id) {
        UUID uuid = UUID.fromString(id);
        try {
            Paciente paciente = service.findById(uuid);
            var dto = mapper.pacienteToRetornarPacienteDTO(paciente);
            return ResponseEntity.ok(dto);
        } catch (PacienteNaoEncontradoException e) {
            log.error(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarPaciente(
            @PathVariable String id,
            @RequestBody SalvarPacienteDTO dto) {
        Paciente paciente = mapper.salvarPacienteDTOtoPaciente(dto);
        try {
            service.update(UUID.fromString(id), paciente);
            return ResponseEntity.noContent().build();
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
