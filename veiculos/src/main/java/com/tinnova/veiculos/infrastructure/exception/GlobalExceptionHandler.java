package com.tinnova.veiculos.infrastructure.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Classe responsável pelo tratamento global de exceções no sistema.
 *
 * <p>
 * Esta classe captura exceções específicas e gerais, e retorna uma resposta com o código de status HTTP apropriado,
 * uma descrição do erro e uma mensagem detalhada. Ela usa a anotação {@code @RestControllerAdvice} para fornecer
 * um tratamento centralizado de exceções em toda a aplicação.
 * </p>
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(VeiculoNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleVeiculoNotFound(VeiculoNotFoundException ex) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, "Veículo não encontrado", ex.getMessage());
    }

    @ExceptionHandler(MarcaInvalidaException.class)
    public ResponseEntity<Map<String, Object>> handleMarcaInvalida(MarcaInvalidaException ex) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "Marca inválida", ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno do servidor", ex.getMessage());
    }

    private ResponseEntity<Map<String, Object>> buildErrorResponse(HttpStatus status, String erro, String descricao) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", status.value());
        response.put("erro", erro);
        response.put("descricao", descricao);
        return new ResponseEntity<>(response, status);
    }
}
