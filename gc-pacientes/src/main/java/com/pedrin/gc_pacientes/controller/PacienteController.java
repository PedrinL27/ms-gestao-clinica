package com.pedrin.gc_pacientes.controller;

import com.pedrin.gc_pacientes.controller.dto.StatusResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @GetMapping("/status")
    public ResponseEntity<StatusResponse> status() {
        return ResponseEntity.ok(new StatusResponse(
                "Status OK - Paciente",
                LocalDateTime.now()
        ));
    }
}
