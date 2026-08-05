package com.pedrin.gc_medicos.controller.dto;

import java.time.LocalDateTime;

public record StatusResponse(
        String mensagem,
        LocalDateTime data
) {
}
