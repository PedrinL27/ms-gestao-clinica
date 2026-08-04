package com.pedrin.gc_clientes.controller;

import com.pedrin.gc_clientes.controller.dto.StatusResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @GetMapping("/status")
    public ResponseEntity<StatusResponse> status() {
        StatusResponse response = new StatusResponse(
                "Status OK - Cliente",
                LocalDateTime.now()
        );

        return ResponseEntity.ok(response);
    }

}
