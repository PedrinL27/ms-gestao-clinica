package com.pedrin.gc_pacientes.controller.dto;

import java.time.LocalDateTime;

public record StatusResponse(
        String mensagem,
        LocalDateTime data
) {
}
