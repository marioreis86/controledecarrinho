package com.varejista.controledecarrinho.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<Object> handleAccessDeniedException(Exception ex, WebRequest request) {
        return new ResponseEntity<Object>(
                "Acesso negado ao recurso", new HttpHeaders(), HttpStatus.FORBIDDEN
        );
    }

    @ExceptionHandler({ObjectNotFoundException.class})
    public ResponseEntity<ApiError> objectNotFound(ObjectNotFoundException objException, WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    /*@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> validation(MethodArgumentNotValidException e, WebRequest request) {

        ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST, "Erro de Validação", LocalDateTime.now());

        for (FieldError fieldError : e.getBindingResult().getFieldErrors())
            err.addError(fieldError.getField(), fieldError.getDefaultMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }*/
}
