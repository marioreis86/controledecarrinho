package com.varejista.controledecarrinho.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ApiError {
    private HttpStatus status;
    private String mensagem;
    private LocalDateTime tempo;

    public ApiError(HttpStatus status, String mensagem, LocalDateTime tempo) {
        this.status = status;
        this.mensagem = mensagem;
        this.tempo = tempo;
    }
}
