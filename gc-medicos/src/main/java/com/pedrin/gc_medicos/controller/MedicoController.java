package com.pedrin.gc_medicos.controller;

import com.pedrin.gc_medicos.controller.dto.StatusResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/medico")
public class MedicoController {

    @GetMapping("/status")
    public ResponseEntity<StatusResponse> status() {
        return ResponseEntity.ok(new StatusResponse(
                "Status OK - Medico",
                LocalDateTime.now()
        ));
    }
}
