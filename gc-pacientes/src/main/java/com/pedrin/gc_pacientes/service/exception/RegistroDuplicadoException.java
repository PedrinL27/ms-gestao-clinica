package com.pedrin.gc_pacientes.service.exception;

import lombok.Getter;

@Getter
public class RegistroDuplicadoException extends RuntimeException {
    private final String field;

    public RegistroDuplicadoException(String message, String field) {
        this.field = field;
        super(message);
    }
}
