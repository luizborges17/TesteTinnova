package com.tinnova.veiculos.infrastructure.exception;

public class MarcaInvalidaException extends RuntimeException {
    public MarcaInvalidaException() {
        super("A marca é invalida.");
    }
}
