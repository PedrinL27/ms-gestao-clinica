package com.pedrin.gc_clientes.controller.dto;

import java.time.LocalDateTime;

public record StatusResponse(
        String mensagem,
        LocalDateTime data
) {
}
