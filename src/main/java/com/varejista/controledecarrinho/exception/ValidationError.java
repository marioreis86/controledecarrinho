package com.varejista.controledecarrinho.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends ApiError {
    private static final long serialVersionUID = 1L;

    @Getter
    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(HttpStatus status, String msg, LocalDateTime tempo) {
        super(status, msg, tempo);
    }

    public void addError(String fieldName, String message) {
        errors.add(new FieldMessage(fieldName, message));
    }
}
